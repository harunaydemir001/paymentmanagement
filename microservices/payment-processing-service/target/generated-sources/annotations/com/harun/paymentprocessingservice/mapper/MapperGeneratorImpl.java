package com.harun.paymentprocessingservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.BankUserDTO;
import com.harun.common.dto.PaymentDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Payment;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T23:20:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public PaymentDTO paymentToPaymentDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setId( payment.getId() );
        paymentDTO.setCreatedAt( payment.getCreatedAt() );
        paymentDTO.setUpdatedAt( payment.getUpdatedAt() );
        paymentDTO.setAmount( payment.getAmount() );

        return paymentDTO;
    }

    @Override
    public Account accountDTOToAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountDTO.getId() );
        account.setCreatedAt( accountDTO.getCreatedAt() );
        account.setUpdatedAt( accountDTO.getUpdatedAt() );
        account.setAccountType( accountDTO.getAccountType() );
        account.setBalance( accountDTO.getBalance() );
        account.setIban( accountDTO.getIban() );
        account.setBankUser( accountDTO.getBankUser() );

        return account;
    }

    @Override
    public BankUser bankUserDTOToBankUser(BankUserDTO bankUserDTO) {
        if ( bankUserDTO == null ) {
            return null;
        }

        BankUser bankUser = new BankUser();

        bankUser.setId( bankUserDTO.getId() );
        bankUser.setCreatedAt( bankUserDTO.getCreatedAt() );
        bankUser.setUpdatedAt( bankUserDTO.getUpdatedAt() );
        bankUser.setEmail( bankUserDTO.getEmail() );
        bankUser.setFirstName( bankUserDTO.getFirstName() );
        bankUser.setLastName( bankUserDTO.getLastName() );
        bankUser.setPhoneNumber( bankUserDTO.getPhoneNumber() );
        bankUser.setGender( bankUserDTO.getGender() );
        bankUser.setEmailVerified( bankUserDTO.isEmailVerified() );
        bankUser.setPhoneVerified( bankUserDTO.isPhoneVerified() );
        bankUser.setOccupation( bankUserDTO.getOccupation() );
        bankUser.setMonthlyIncome( bankUserDTO.getMonthlyIncome() );
        bankUser.setCreditScore( bankUserDTO.getCreditScore() );
        Set<Account> set = bankUserDTO.getAccounts();
        if ( set != null ) {
            bankUser.setAccounts( new LinkedHashSet<Account>( set ) );
        }

        return bankUser;
    }
}
