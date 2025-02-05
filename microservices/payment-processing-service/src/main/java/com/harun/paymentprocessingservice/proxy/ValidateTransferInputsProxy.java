package com.harun.paymentprocessingservice.proxy;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.enums.ErrorMessage;
import com.harun.common.feign.impl.AccountServiceClientImpl;
import com.harun.common.utils.StringBuilderUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidateTransferInputsProxy {
    private final AccountServiceClientImpl accountServiceClientImpl;

    @Before(value = "execution(* com.harun.paymentprocessingservice.service.impl.PaymentServiceImpl.processPayment(..)) && args(paymentRequest)")
    public void validateTransferInputs(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        validateTransferInputs(paymentRequest.getSourceAccountId(), paymentRequest.getTargetAccountId(), paymentRequest.getAmount());
    }

    private void validateTransferInputs(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        validateAccountIds(sourceAccountId, targetAccountId);
        validateTransferAmount(amount);
        validateAccountExistence(sourceAccountId, targetAccountId);
        validateSufficientBalance(sourceAccountId, amount);
    }

    private void validateAccountIds(Long sourceAccountId, Long targetAccountId) {
        if (Objects.isNull(sourceAccountId) || Objects.isNull(targetAccountId)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NULL.getMessage("Account IDs"));
        }

        if (sourceAccountId.equals(targetAccountId)) {
            throw new IllegalArgumentException("Source and target accounts must be different.");
        }
    }

    private void validateTransferAmount(BigDecimal amount) {
        if (Objects.isNull(amount)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NULL.getMessage("Amount"));
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
    }

    private void validateAccountExistence(Long sourceAccountId, Long targetAccountId) {
        if (Objects.isNull(accountServiceClientImpl.getAccountById(sourceAccountId))) {
            throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_WITH_ID.getMessage("Account: ", sourceAccountId));
        }

        if (Objects.isNull(accountServiceClientImpl.getAccountById(targetAccountId))) {
            throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_WITH_ID.getMessage("Account: ", targetAccountId));
        }
    }

    private void validateSufficientBalance(Long sourceAccountId, BigDecimal amount) {
        AccountDTO sourceAccountDTO = accountServiceClientImpl.getAccountById(sourceAccountId);

        if (sourceAccountDTO.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException(StringBuilderUtil.buildMessage("Insufficient balance in the source account: {}", sourceAccountId));
        }
    }
}

