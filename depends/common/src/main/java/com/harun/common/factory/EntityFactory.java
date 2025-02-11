package com.harun.common.factory;


import com.harun.entity.embeddables.AmountEmbed;
import com.harun.entity.enums.TransactionType;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Payment;
import com.harun.entity.models.Transaction;

import java.math.BigDecimal;

public class EntityFactory {

    private EntityFactory() {
    }

    public static Transaction createTransaction(BigDecimal amount, TransactionType transactionType, Account fromAccount, Account toAccount, BankUser bankUser) {
        return Transaction.builder()
                .withAmount(new AmountEmbed(amount))
                .withTransactionType(transactionType)
                .withFromAccount(fromAccount)
                .withToAccount(toAccount)
                .withBankUser(bankUser)
                .build();
    }

    public static Payment createPayment(BigDecimal amount, Long transactionId, Long accountId) {
        return Payment.builder()
                .withAmount(new AmountEmbed(amount))
                .withTransactionId(transactionId)
                .withAccountId(accountId)
                .build();
    }
}
