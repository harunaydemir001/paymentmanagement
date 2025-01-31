package com.harun.usermanagementservice.mapper;

import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.usermanagementservice.dto.BankUserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-31T21:20:48+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public BankUserDTO userToUserDTO(BankUser bankUser) {
        if ( bankUser == null ) {
            return null;
        }

        BankUserDTO bankUserDTO = new BankUserDTO();

        bankUserDTO.setId( bankUser.getId() );
        List<Account> list = bankUser.getAccounts();
        if ( list != null ) {
            bankUserDTO.setAccounts( new ArrayList<Account>( list ) );
        }

        return bankUserDTO;
    }

    @Override
    public List<BankUserDTO> UserListToUserDTOList(List<BankUser> allBankUsers) {
        if ( allBankUsers == null ) {
            return null;
        }

        List<BankUserDTO> list = new ArrayList<BankUserDTO>( allBankUsers.size() );
        for ( BankUser bankUser : allBankUsers ) {
            list.add( userToUserDTO( bankUser ) );
        }

        return list;
    }
}
