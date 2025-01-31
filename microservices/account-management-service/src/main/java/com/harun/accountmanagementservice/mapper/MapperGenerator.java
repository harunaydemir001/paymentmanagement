package com.harun.accountmanagementservice.mapper;


import com.harun.common.dto.AccountDTO;
import com.harun.entity.models.Account;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    AccountDTO accountToAccountDTO(Account account);

    List<AccountDTO> accountListToAccountDTOList(List<Account> accounts);
}