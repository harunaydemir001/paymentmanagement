package com.harun.moneytransferservice.mapper;


import com.harun.common.dto.TransactionDTO;
import com.harun.entity.models.Transaction;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    TransactionDTO transactionToTransactionDTO(Transaction transaction);
}