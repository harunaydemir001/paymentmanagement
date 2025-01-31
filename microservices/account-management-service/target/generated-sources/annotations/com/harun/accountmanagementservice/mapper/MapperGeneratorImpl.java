package com.harun.accountmanagementservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-31T21:09:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public AccountDTO accountToAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId( account.getId() );
        accountDTO.setAccountNumber( account.getAccountNumber() );
        accountDTO.setAccountType( account.getAccountType() );
        accountDTO.setBalance( account.getBalance() );
        accountDTO.setBankUser( account.getBankUser() );
        List<Payment> list = account.getPayments();
        if ( list != null ) {
            accountDTO.setPayments( new ArrayList<Payment>( list ) );
        }

        return accountDTO;
    }

    @Override
    public List<AccountDTO> accountListToAccountDTOList(List<Account> accounts) {
        if ( accounts == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( accounts.size() );
        for ( Account account : accounts ) {
            list.add( accountToAccountDTO( account ) );
        }

        return list;
    }
}
