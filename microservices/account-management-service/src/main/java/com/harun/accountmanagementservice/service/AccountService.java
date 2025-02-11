package com.harun.accountmanagementservice.service;

import com.harun.common.dto.AccountDTO;

public interface AccountService {

    AccountDTO getAccountById(Long id);

    AccountDTO updateAccount(AccountDTO accountDTO);

    AccountDTO saveAccount(AccountDTO accountDTO);

    void deleteAccount(Long id);
}
