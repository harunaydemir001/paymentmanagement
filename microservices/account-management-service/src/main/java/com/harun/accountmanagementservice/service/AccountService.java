package com.harun.accountmanagementservice.service;

import com.harun.common.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    AccountDTO getAccountById(Long id);

    AccountDTO updateAccount(AccountDTO accountDTO);

    AccountDTO saveAccount(AccountDTO accountDTO);

    void deleteAccount(Long id);

    Page<AccountDTO> filter(Pageable pageable, AccountDTO directorDTO);
}
