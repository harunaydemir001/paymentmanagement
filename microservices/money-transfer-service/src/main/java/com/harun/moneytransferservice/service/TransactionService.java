package com.harun.moneytransferservice.service;

import com.harun.common.dto.TransactionDTO;
import com.harun.entity.models.Transaction;

public interface TransactionService {
    TransactionDTO getTransactionById(Long id);

    TransactionDTO updateTransaction(Transaction transaction);

    TransactionDTO saveTransaction(Transaction transaction);

    void deleteTransaction(Long id);
}
