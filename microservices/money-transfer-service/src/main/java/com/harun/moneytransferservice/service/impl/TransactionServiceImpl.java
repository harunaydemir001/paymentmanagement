package com.harun.moneytransferservice.service.impl;

import com.harun.common.dto.TransactionDTO;
import com.harun.common.enums.ErrorMessage;
import com.harun.common.feign.impl.AccountServiceClientImpl;
import com.harun.entity.models.Transaction;
import com.harun.moneytransferservice.mapper.MapperGenerator;
import com.harun.moneytransferservice.mapper.MapperGeneratorSingleton;
import com.harun.moneytransferservice.repository.TransactionRepository;
import com.harun.moneytransferservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private static final String TRANSACTION = "Transaction";
    private String message = "";

    private final TransactionRepository transactionRepository;
    private final AccountServiceClientImpl accountServiceClientImpl;

    @Override
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findByIdOrThrowError(id);
        return mapper.transactionToTransactionDTO(transaction);
    }

    @Override
    public TransactionDTO updateTransaction(Transaction transaction) {
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return mapper.transactionToTransactionDTO(updatedTransaction);
    }

    @Override
    public TransactionDTO saveTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapper.transactionToTransactionDTO(savedTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.findByIdOrThrowError(id);
        message = ErrorMessage.DELETION_SUCCESS.getMessage(TRANSACTION, id);
        logger.info(message);
        transactionRepository.deleteById(id);
    }
}

