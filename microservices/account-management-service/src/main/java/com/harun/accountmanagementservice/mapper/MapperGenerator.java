package com.harun.accountmanagementservice.mapper;


import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.BankUserDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    @Mapping(target = "bankUserId", source = "bankUser.id")
    AccountDTO accountToAccountDTO(Account account);

    List<AccountDTO> accountListToAccountDTOList(List<Account> accounts);

    BankUser bankUserDTOToBankUser(BankUserDTO bankUserDTO);

    @Mapping(target = "bankUser", ignore = true)
    Account accountDTOToAccount(AccountDTO accountDTO);
}