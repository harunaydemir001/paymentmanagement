package com.harun.moneytransferservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.TransactionDTO;
import com.harun.common.models.Account;
import com.harun.common.models.Notification;
import com.harun.common.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-27T23:53:09+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setAmount( transaction.getAmount() );
        transactionDTO.setType( transaction.getType() );
        transactionDTO.setSourceAccount( transaction.getSourceAccount() );
        transactionDTO.setTargetAccount( transaction.getTargetAccount() );
        List<Notification> list = transaction.getNotifications();
        if ( list != null ) {
            transactionDTO.setNotifications( new ArrayList<Notification>( list ) );
        }

        return transactionDTO;
    }

    @Override
    public Account accountDTOToAccount(AccountDTO sourceAccount) {
        if ( sourceAccount == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( sourceAccount.getId() );
        account.setAccountNumber( sourceAccount.getAccountNumber() );
        account.setBalance( sourceAccount.getBalance() );
        account.setBankUser( sourceAccount.getBankUser() );
        List<Transaction> list = sourceAccount.getOutgoingTransactions();
        if ( list != null ) {
            account.setOutgoingTransactions( new ArrayList<Transaction>( list ) );
        }
        List<Transaction> list1 = sourceAccount.getIncomingTransactions();
        if ( list1 != null ) {
            account.setIncomingTransactions( new ArrayList<Transaction>( list1 ) );
        }

        return account;
    }
}
