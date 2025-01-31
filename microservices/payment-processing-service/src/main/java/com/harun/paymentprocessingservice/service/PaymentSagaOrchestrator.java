package com.harun.paymentprocessingservice.service;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.EmailRequest;
import com.harun.common.dto.ReportDTO;
import com.harun.common.enums.EventType;
import com.harun.common.feign.impl.AccountServiceClientImpl;
import com.harun.common.feign.impl.MoneyTransferServiceClientImpl;
import com.harun.common.kafka.KafkaProducer;
import com.harun.common.kafka.KafkaTopicsProperties;
import com.harun.common.utils.StringBuilderUtil;
import com.harun.entity.enums.TransactionType;
import com.harun.entity.models.Transaction;
import com.harun.paymentprocessingservice.enums.PaymentSagaStep;
import com.harun.paymentprocessingservice.mapper.MapperGenerator;
import com.harun.paymentprocessingservice.mapper.MapperGeneratorSingleton;
import com.harun.paymentprocessingservice.model.PaymentSagaState;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentSagaOrchestrator {

    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final PaymentService paymentService;
    private final AccountServiceClientImpl accountServiceClientImpl;
    private final MoneyTransferServiceClientImpl moneyTransferServiceClientImpl;
    private final PaymentSagaState paymentSagaState;
    private final KafkaProducer kafkaProducer;

    private static final Logger logger = LoggerFactory.getLogger(PaymentSagaOrchestrator.class);
    private static String message = "Transfer is {}: Source Account ID: {} Target Account ID: {} Amount: {}";

    public PaymentSagaState processPayment(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {

        try {
            logger.info(message, "started", sourceAccountId, targetAccountId, amount);

            initializePaymentSagaState(sourceAccountId, targetAccountId, amount);

            validateInputs();

            debitSourceAccount();

            creditTargetAccount();

            saveTransaction();

            paymentSagaState.setCurrentStep(PaymentSagaStep.COMPLETE_PAYMENT);

            logger.info(message, "completed", sourceAccountId, targetAccountId, amount);

            sendEmail();
            createReport();

            return paymentSagaState;

        } catch (Exception e) {
            handleFailure(e);
        }
        return null;
    }

    private void createReport() {
        var reportDTO = createReportDTO();
        kafkaProducer.sendKafkaMessage(reportDTO, KafkaTopicsProperties.getReportTopic());
    }

    private ReportDTO createReportDTO() {
        return ReportDTO.builder()
                .withEventType(EventType.MONEY_TRANSFER)
                .withMessage(StringBuilderUtil.buildMessage(message, "completed",
                        paymentSagaState.getSourceAccountId(),
                        paymentSagaState.getTargetAccountId(),
                        paymentSagaState.getAmount()))
                .build();
    }

    private void sendEmail() {
        var sourceEmailRequest = createEmailRequest(paymentSagaState.getSourceAccountId(),
                "{} amount of money has been transferred from target account.", paymentSagaState.getAmount());

        var targetEmailRequest = createEmailRequest(paymentSagaState.getTargetAccountId(),
                "{} amount of money has arrived in your account.", paymentSagaState.getAmount());

        kafkaProducer.sendKafkaMessage(sourceEmailRequest, KafkaTopicsProperties.getEmailTopic());
        kafkaProducer.sendKafkaMessage(targetEmailRequest, KafkaTopicsProperties.getEmailTopic());
    }

    private EmailRequest createEmailRequest(Long sourceAccountId, String message, BigDecimal amount) {
        return EmailRequest.builder()
                .withRecipient(getAccountById(sourceAccountId).getBankUser().getEmail())
                .withMsgBody(StringBuilderUtil.buildMessage(message, amount))
                .withSubject("Money Transfer")
                .withAttachment(null)
                .build();
    }

    private void initializePaymentSagaState(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        paymentSagaState.setCurrentStep(PaymentSagaStep.START_PAYMENT);
        paymentSagaState.setAmount(amount);
        paymentSagaState.setSourceAccountId(sourceAccountId);
        paymentSagaState.setTargetAccountId(targetAccountId);
    }


    private void validateInputs() {
        paymentSagaState.setCurrentStep(PaymentSagaStep.VALIDATE_INPUTS);
        paymentService.validateTransferInputs(paymentSagaState.getSourceAccountId(), paymentSagaState.getTargetAccountId(), paymentSagaState.getAmount());
        logger.info("Transfer inputs validated.");
    }

    private void debitSourceAccount() {
        paymentSagaState.setCurrentStep(PaymentSagaStep.DEBIT_SOURCE_ACCOUNT);
        AccountDTO sourceAccountDTO = getAccountById(paymentSagaState.getSourceAccountId());
        sourceAccountDTO.setBalance(sourceAccountDTO.getBalance().subtract(paymentSagaState.getAmount()));
        accountServiceClientImpl.updateAccount(mapper.accountDTOToAccount(sourceAccountDTO));
        logger.info("Source account debited.");
    }

    private void creditTargetAccount() {
        paymentSagaState.setCurrentStep(PaymentSagaStep.CREDIT_TARGET_ACCOUNT);
        AccountDTO targetAccountDTO = getAccountById(paymentSagaState.getTargetAccountId());
        targetAccountDTO.setBalance(targetAccountDTO.getBalance().add(paymentSagaState.getAmount()));
        accountServiceClientImpl.updateAccount(mapper.accountDTOToAccount(targetAccountDTO));
        logger.info("Target account credited.");
    }

    private void saveTransaction() {
        paymentSagaState.setCurrentStep(PaymentSagaStep.SAVE_TRANSACTION);
        moneyTransferServiceClientImpl.saveTransaction(createTransactionEntity());
        logger.info("Transaction saved.");
    }

    private void handleFailure(Exception e) {
        paymentSagaState.setCurrentStep(PaymentSagaStep.FAILURE_PAYMENT);
        paymentSagaState.setFailureReason(e.getMessage());
        logger.error("Payment failed: {}", e.getMessage());
        compensate();
    }

    private void compensate() {
        switch (paymentSagaState.getCurrentStep()) {
            case CREDIT_TARGET_ACCOUNT:
                compensationTargetAccount();
                break;
            case DEBIT_SOURCE_ACCOUNT:
                compensationSourceAccount();
                break;
            case SAVE_TRANSACTION:
                compensationTargetAccount();
                compensationSourceAccount();
                break;
            default:
                logger.info("No compensation needed for step: {}", paymentSagaState.getCurrentStep());
                break;
        }
    }

    private void compensationSourceAccount() {
        AccountDTO sourceAccountDTO = getAccountById(paymentSagaState.getSourceAccountId());
        sourceAccountDTO.setBalance(sourceAccountDTO.getBalance().add(paymentSagaState.getAmount()));
        accountServiceClientImpl.updateAccount(mapper.accountDTOToAccount(sourceAccountDTO));
        logger.info("Compensation: Source account credited.");
    }

    private void compensationTargetAccount() {
        AccountDTO targetAccountDTO = getAccountById(paymentSagaState.getTargetAccountId());
        targetAccountDTO.setBalance(targetAccountDTO.getBalance().subtract(paymentSagaState.getAmount()));
        accountServiceClientImpl.updateAccount(mapper.accountDTOToAccount(targetAccountDTO));
        logger.info("Compensation: Target account debited.");
    }

    private Transaction createTransactionEntity() {
        return Transaction.builder()
                .withAmount(paymentSagaState.getAmount())
                .withTransactionType(TransactionType.PAYMENT)
                .withFromAccount(mapper.accountDTOToAccount(getAccountById(paymentSagaState.getSourceAccountId())))
                .withToAccount(mapper.accountDTOToAccount(getAccountById(paymentSagaState.getTargetAccountId())))
                .build();
    }

    //cachle
    private AccountDTO getAccountById(Long sourceAccountId) {
        return accountServiceClientImpl.getAccountById(sourceAccountId);
    }
}