package com.harun.paymentprocessingservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.BankUserDTO;
import com.harun.common.dto.CardDTO;
import com.harun.common.dto.PaymentDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Card;
import com.harun.entity.models.Payment;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-14T23:35:18+0300",
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
        paymentDTO.setAccountId( payment.getAccountId() );
        paymentDTO.setTransactionId( payment.getTransactionId() );

        return paymentDTO;
    }

    @Override
    public List<PaymentDTO> paymentToPaymentDTO(List<Payment> payment) {
        if ( payment == null ) {
            return null;
        }

        List<PaymentDTO> list = new ArrayList<PaymentDTO>( payment.size() );
        for ( Payment payment1 : payment ) {
            list.add( paymentToPaymentDTO( payment1 ) );
        }

        return list;
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
        account.setVersion( accountDTO.getVersion() );

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

    @Override
    public CardDTO cardToCardDTO(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();

        cardDTO.setId( card.getId() );
        cardDTO.setCreatedAt( card.getCreatedAt() );
        cardDTO.setUpdatedAt( card.getUpdatedAt() );
        cardDTO.setCardNumber( card.getCardNumber() );
        cardDTO.setExpiryDate( card.getExpiryDate() );
        cardDTO.setCvv( card.getCvv() );
        cardDTO.setAccount( card.getAccount() );
        cardDTO.setBankUser( card.getBankUser() );

        return cardDTO;
    }

    @Override
    public List<CardDTO> cardToCardDTO(List<Card> cards) {
        if ( cards == null ) {
            return null;
        }

        List<CardDTO> list = new ArrayList<CardDTO>( cards.size() );
        for ( Card card : cards ) {
            list.add( cardToCardDTO( card ) );
        }

        return list;
    }
}
