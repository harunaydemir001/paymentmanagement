package com.harun.paymentprocessingservice.service.impl;

import com.harun.common.dto.PaymentDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.dto.PaymentSagaState;
import com.harun.common.enums.ErrorMessage;
import com.harun.common.factory.EntityFactory;
import com.harun.common.feign.impl.AccountServiceClientImpl;
import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.mapper.MapperGenerator;
import com.harun.paymentprocessingservice.mapper.MapperGeneratorSingleton;
import com.harun.paymentprocessingservice.repository.PaymentRepository;
import com.harun.paymentprocessingservice.service.PaymentSagaOrchestrator;
import com.harun.paymentprocessingservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private static final String PAYMENT = "Payment";
    private static String message = "";

    private final PaymentRepository paymentRepository;
    private final AccountServiceClientImpl accountServiceClientImpl;
    private final PaymentSagaOrchestrator paymentSagaOrchestrator;

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
    public PaymentSagaState processPayment(PaymentRequest paymentRequest) {
        PaymentSagaState paymentSagaState = paymentSagaOrchestrator.processPayment(
                paymentRequest.getSourceAccountId(),
                paymentRequest.getTargetAccountId(),
                paymentRequest.getAmount());
        Payment sourcePayment = EntityFactory.createPayment(
                paymentSagaState.getAmount(),
                paymentSagaState.getTransactionId(),
                paymentSagaState.getSourceAccountId());
        savePayment(sourcePayment);
        Payment targetPayment = EntityFactory.createPayment(
                paymentSagaState.getAmount(),
                paymentSagaState.getTransactionId(),
                paymentSagaState.getTargetAccountId());
        savePayment(targetPayment);
        return paymentSagaState;
    }

    @Override
    public PaymentDTO payInvoice(Double amount) {
        return null;
    }
}
