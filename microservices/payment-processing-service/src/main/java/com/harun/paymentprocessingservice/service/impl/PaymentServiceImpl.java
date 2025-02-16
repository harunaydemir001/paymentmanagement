package com.harun.paymentprocessingservice.service.impl;

import com.harun.common.dto.PaymentDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.dto.PaymentSagaState;
import com.harun.common.enums.ErrorMessage;
import com.harun.common.enums.PaymentSagaStep;
import com.harun.common.factory.EntityFactory;
import com.harun.common.kafka.KafkaProducer;
import com.harun.common.kafka.KafkaTopicsProperties;
import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.mapper.MapperGenerator;
import com.harun.paymentprocessingservice.mapper.MapperGeneratorSingleton;
import com.harun.paymentprocessingservice.mapper.PageMapper;
import com.harun.paymentprocessingservice.model.PayRequest;
import com.harun.paymentprocessingservice.repository.PaymentRepository;
import com.harun.paymentprocessingservice.saga.PaymentSagaOrchestrator;
import com.harun.paymentprocessingservice.service.PaymentService;
import com.harun.paymentprocessingservice.strategy.PayStrategy;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private static final String PAYMENT = "Payment";
    private static String message = "";

    private final PaymentRepository paymentRepository;
    private final PaymentSagaOrchestrator paymentSagaOrchestrator;
    private final Map<String, PayStrategy> strategies;
    private final PaymentRepository.PaymentBatchRepository paymentBatchRepository;

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findByIdOrThrowError(id);
        return mapper.paymentToPaymentDTO(payment);
    }

    @Override
    public PaymentDTO updatePayment(Payment payment) {
        Payment updatedPayment = paymentRepository.save(payment);
        return mapper.paymentToPaymentDTO(updatedPayment);
    }

    @Override
    public PaymentDTO savePayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return mapper.paymentToPaymentDTO(savedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.findByIdOrThrowError(id);
        message = ErrorMessage.DELETION_SUCCESS.getMessage(PAYMENT, id);
        logger.info(message);
        paymentRepository.deleteById(id);
    }

    @Override
//    @Bulkhead(name = "default")
//    @TimeLimiter(name = "default")
//    @RateLimiter(name = "default")
    public PaymentSagaState processPayment(PaymentRequest paymentRequest) {
        PaymentSagaState paymentSagaState = paymentSagaOrchestrator.processPayment(
                paymentRequest.getSourceAccountId(),
                paymentRequest.getTargetAccountId(),
                paymentRequest.getAmount());
        if (Objects.equals(paymentSagaState.getCurrentStep(), PaymentSagaStep.COMPLETE_PAYMENT)) {
            createPaymentAndSendKafka(paymentSagaState, paymentSagaState.getSourceAccountId());
            createPaymentAndSendKafka(paymentSagaState, paymentSagaState.getSourceAccountId());
        }
        return paymentSagaState;
    }

    private void createPaymentAndSendKafka(PaymentSagaState paymentSagaState, Long accountId) {
        Payment payment = EntityFactory.createPayment(
                paymentSagaState.getAmount(),
                paymentSagaState.getTransactionId(),
                accountId);
        KafkaProducer.sendKafkaMessage(payment, KafkaTopicsProperties.getPaymentTopic());
    }

    //Diğer ödeme seçenekleri eklenebilir.
    public void pay(PayRequest payRequest) {
        PayStrategy payStrategy = strategies.get(payRequest.getPayType().getDescription());
        switch (payRequest.getPayType()) {
            case CARD:
                payStrategy.pay(payRequest.getAmount(), payRequest.getSourceCard(), payRequest.getTargetAccountId());
                break;
            case EFT:
                payStrategy.pay(payRequest.getAmount());
                break;
            default:
                break;
        }
    }

    @Override
    public Page<PaymentDTO> filter(Pageable pageable, PaymentDTO directorDTO) {
        Page<Payment> page = paymentRepository.findByFilter(pageable, directorDTO);
        List<PaymentDTO> directorDTOList = mapper.paymentToPaymentDTO(page.getContent());
        return PageMapper.toPage(page, directorDTOList);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processBatch(List<Payment> payments) {
        paymentBatchRepository.batchInsert(payments);
    }

    @Override
    @CircuitBreaker(name = "backendB", fallbackMethod = "payInvoiceFallBack")
    //Eğer çok fazla hata alırsa, belirli bir süre için çağrıları engeller ve payInvoiceFallBack metoduna yönlendirir.
    @Retry(name = "default")
    //Belirli hatalar alındığında isteği tekrar denemeyi sağlar.(HttpServerErrorException veya IOException)
    // (maxRetryAttempts: 3, waitDuration: 500ms).
    @Bulkhead(name = "default")
    //Aynı anda çalıştırılabilecek maksimum işlem sayısını sınırlar.
    //maxConcurrentCalls: 10 değeriyle, aynı anda en fazla 10 isteğin çalışmasına izin verir.
    @TimeLimiter(name = "default")
    //İsteklerin belirli bir sürede tamamlanmasını zorunlu kılar.
    //timeout-duration: 10s ayarına göre, 10 saniye içinde tamamlanmayan işlemler iptal edilir.
    @RateLimiter(name = "default")
    //Belli bir zaman aralığında yapılan istek sayısını sınırlar (Rate Limiting).
    //limit-for-period: 10, yani her saniyede en fazla 10 isteğe izin verir.
    public PaymentDTO payInvoice(Double amount) {
        return null;
    }

    private PaymentDTO payInvoiceFallBack(PaymentRequest paymentRequest, Exception e) {
        logger.info("FallBack work for pay invoice due to: " + e.getMessage());
        return new PaymentDTO();
    }
}
