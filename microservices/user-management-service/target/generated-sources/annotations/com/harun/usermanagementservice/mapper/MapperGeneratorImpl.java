package com.harun.usermanagementservice.mapper;

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
    date = "2025-02-14T23:36:38+0300",
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
        bankUserDTO.setCreatedAt( bankUser.getCreatedAt() );
        bankUserDTO.setUpdatedAt( bankUser.getUpdatedAt() );
        bankUserDTO.setEmail( bankUser.getEmail() );
        bankUserDTO.setFirstName( bankUser.getFirstName() );
        bankUserDTO.setLastName( bankUser.getLastName() );
        bankUserDTO.setPhoneNumber( bankUser.getPhoneNumber() );
        bankUserDTO.setGender( bankUser.getGender() );
        bankUserDTO.setEmailVerified( bankUser.isEmailVerified() );
        bankUserDTO.setPhoneVerified( bankUser.isPhoneVerified() );
        bankUserDTO.setOccupation( bankUser.getOccupation() );
        bankUserDTO.setMonthlyIncome( bankUser.getMonthlyIncome() );
        bankUserDTO.setCreditScore( bankUser.getCreditScore() );
        Set<Account> set = bankUser.getAccounts();
        if ( set != null ) {
            bankUserDTO.setAccounts( new LinkedHashSet<Account>( set ) );
        }

        return bankUserDTO;
    }

    @Override
    public List<BankUserDTO> userToUserDTO(List<BankUser> allBankUsers) {
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
