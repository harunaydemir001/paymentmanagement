package com.harun.moneytransferservice.mapper;


import com.harun.common.dto.AccountDTO;
import com.harun.common.models.Account;
import com.harun.common.models.Transaction;
import com.harun.common.dto.TransactionDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    TransactionDTO transactionToTransactionDTO(Transaction transaction);

//    Account accountDTOToAccount(AccountDTO sourceAccount);
}