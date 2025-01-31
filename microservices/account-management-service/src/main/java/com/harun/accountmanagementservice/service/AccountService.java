package com.harun.accountmanagementservice.service;

import com.harun.common.dto.AccountDTO;
import com.harun.entity.models.Account;

import java.util.List;

public interface AccountService {

    AccountDTO getAccountById(Long id);

    AccountDTO updateAccount(Account account);

    AccountDTO saveAccount(Account account);

    void deleteAccount(Long id);

    List<AccountDTO> getAccountsByUserId(Long userId);
}
