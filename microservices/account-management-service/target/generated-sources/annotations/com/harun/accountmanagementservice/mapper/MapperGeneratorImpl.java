package com.harun.accountmanagementservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.BankUserDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T12:25:42+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public AccountDTO accountToAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setBankUserId( accountBankUserId( account ) );
        accountDTO.setId( account.getId() );
        accountDTO.setCreatedAt( account.getCreatedAt() );
        accountDTO.setUpdatedAt( account.getUpdatedAt() );
        accountDTO.setAccountType( account.getAccountType() );
        accountDTO.setBalance( account.getBalance() );
        accountDTO.setIban( account.getIban() );
        accountDTO.setVersion( account.getVersion() );

        return accountDTO;
    }

    @Override
    public List<AccountDTO> accountToAccountDTO(List<Account> accounts) {
        if ( accounts == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( accounts.size() );
        for ( Account account : accounts ) {
            list.add( accountToAccountDTO( account ) );
        }

        return list;
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

    private Long accountBankUserId(Account account) {
        if ( account == null ) {
            return null;
        }
        BankUser bankUser = account.getBankUser();
        if ( bankUser == null ) {
            return null;
        }
        Long id = bankUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
