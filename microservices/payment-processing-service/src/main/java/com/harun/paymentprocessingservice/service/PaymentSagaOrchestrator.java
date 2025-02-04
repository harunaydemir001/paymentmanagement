package com.harun.paymentprocessingservice.service;

import com.harun.common.dto.AccountDTO;
import com.harun.common.enums.EventType;
import com.harun.common.feign.impl.AccountServiceClientImpl;
import com.harun.common.feign.impl.BankUserServiceClientImpl;
import com.harun.common.feign.impl.MoneyTransferServiceClientImpl;
import com.harun.common.utils.EmailUtil;
import com.harun.common.utils.ReportUtil;
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

    private final AccountServiceClientImpl accountServiceClientImpl;
    private final MoneyTransferServiceClientImpl moneyTransferServiceClientImpl;
    private final BankUserServiceClientImpl bankUserServiceClient;
    private final PaymentSagaState paymentSagaState;


    private static final Logger logger = LoggerFactory.getLogger(PaymentSagaOrchestrator.class);
    private static final String message = "Transfer is {}: Source Account ID: {} Target Account ID: {} Amount: {}";

    public PaymentSagaState processPayment(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {

        try {
            logger.info(message, "started", sourceAccountId, targetAccountId, amount);

            initializePaymentSagaState(sourceAccountId, targetAccountId, amount);

            debitSourceAccount();

            creditTargetAccount();

            saveTransaction();

            paymentSagaState.setCurrentStep(PaymentSagaStep.COMPLETE_PAYMENT);

            logger.info(message, "completed", sourceAccountId, targetAccountId, amount);

            return paymentSagaState;

        } catch (Exception e) {
            handleFailure(e);
        } finally {
            sendEmail();
            createReport();
        }
        return paymentSagaState;
    }

    private void createReport() {
        String status = paymentSagaState.getCurrentStep().equals(PaymentSagaStep.COMPLETE_PAYMENT)
                ? PaymentSagaStep.COMPLETE_PAYMENT.name()
                : PaymentSagaStep.FAILURE_PAYMENT.name();
        ReportUtil.createReport(
                null,
                EventType.PAYMENT_PROCESS,
                StringBuilderUtil.buildMessage(message,
                        status,
                        paymentSagaState.getSourceAccountId(),
                        paymentSagaState.getTargetAccountId(),
                        paymentSagaState.getAmount()),
                null);
    }


    private void sendEmail() {
        boolean isPaymentComplete = paymentSagaState.getCurrentStep().equals(PaymentSagaStep.COMPLETE_PAYMENT);
        String sourceMessage = isPaymentComplete
                ? "{} amount of money has been transferred from target account."
                : "{} amount of money has not been transferred from target account.";
        String targetMessage = isPaymentComplete
                ? "{} amount of money has arrived in your account."
                : "{} amount of money has not arrived in your account.";

        EmailUtil.sendEmail("harunaydemir001@gmail.com",
                StringBuilderUtil.buildMessage(sourceMessage,
                        paymentSagaState.getAmount()),
                EventType.PAYMENT_PROCESS.name(),
                null);
        EmailUtil.sendEmail("harunaydemir001@gmail.com",
                StringBuilderUtil.buildMessage(targetMessage,
                        paymentSagaState.getAmount()),
                EventType.PAYMENT_PROCESS.name(),
                null);
    }


    private void initializePaymentSagaState(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        paymentSagaState.setCurrentStep(PaymentSagaStep.START_PAYMENT);
        paymentSagaState.setAmount(amount);
        paymentSagaState.setSourceAccountId(sourceAccountId);
        paymentSagaState.setTargetAccountId(targetAccountId);
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
                paymentSagaState.setCurrentStep(PaymentSagaStep.FAILURE_PAYMENT);
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
                .withBankUser(mapper.bankUserDTOToBankUser(bankUserServiceClient.getBankUserById(getAccountById(paymentSagaState.getSourceAccountId()).getUserId())))
                .build();
    }

    private AccountDTO getAccountById(Long sourceAccountId) {
        return accountServiceClientImpl.getAccountById(sourceAccountId);
    }
}