package com.harun.accountmanagementservice.mapper;

import com.harun.common.dto.AccountDTO;
import com.harun.entity.models.Account;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-05T14:31:26+0300",
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
        accountDTO.setVersion( account.getVersion() );
        accountDTO.setCreatedAt( account.getCreatedAt() );
        accountDTO.setUpdatedAt( account.getUpdatedAt() );
        accountDTO.setAccountType( account.getAccountType() );
        accountDTO.setBalance( account.getBalance() );
        accountDTO.setIban( account.getIban() );
        accountDTO.setBankUser( account.getBankUser() );
        List<Long> list = account.getTransactionIds();
        if ( list != null ) {
            accountDTO.setTransactionIds( new ArrayList<Long>( list ) );
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
