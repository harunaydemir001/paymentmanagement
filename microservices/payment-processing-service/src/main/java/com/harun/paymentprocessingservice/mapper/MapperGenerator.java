package com.harun.paymentprocessingservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.BankUserDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.dto.PaymentDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    PaymentDTO paymentToPaymentDTO(Payment payment);
    Account accountDTOToAccount(AccountDTO accountDTO);
    BankUser bankUserDTOToBankUser(BankUserDTO bankUserDTO);
}