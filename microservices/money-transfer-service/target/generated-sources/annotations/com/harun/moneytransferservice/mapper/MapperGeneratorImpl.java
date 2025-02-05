package com.harun.moneytransferservice.mapper;

import com.harun.common.dto.TransactionDTO;
import com.harun.entity.models.Transaction;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-05T21:14:12+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId( transaction.getId() );
        transactionDTO.setCreatedAt( transaction.getCreatedAt() );
        transactionDTO.setUpdatedAt( transaction.getUpdatedAt() );
        transactionDTO.setAmount( transaction.getAmount() );
        transactionDTO.setTransactionType( transaction.getTransactionType() );
        transactionDTO.setFromAccount( transaction.getFromAccount() );
        transactionDTO.setToAccount( transaction.getToAccount() );
        transactionDTO.setBankUser( transaction.getBankUser() );

        return transactionDTO;
    }
}
