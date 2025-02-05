package com.harun.common.enums;

public enum PaymentSagaStep {
    START_PAYMENT,
    VALIDATE_INPUTS,
    DEBIT_SOURCE_ACCOUNT,
    CREDIT_TARGET_ACCOUNT,
    SAVE_TRANSACTION,
    COMPLETE_PAYMENT,
    FAILURE_PAYMENT
}