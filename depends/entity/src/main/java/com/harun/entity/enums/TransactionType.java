package com.harun.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT("Deposit", "Funds added to the account"),
    WITHDRAWAL("Withdrawal", "Funds withdrawn from the account"),
    TRANSFER("Transfer", "Funds transferred between accounts"),
    PAYMENT("Payment", "Payment made for a service or product"),
    REFUND("Refund", "Funds refunded to the account");

    private final String displayName;
    private final String description;


    TransactionType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

}

