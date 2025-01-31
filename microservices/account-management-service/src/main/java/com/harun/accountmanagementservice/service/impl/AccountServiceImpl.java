package com.harun.accountmanagementservice.service.impl;

import com.harun.accountmanagementservice.mapper.MapperGenerator;
import com.harun.accountmanagementservice.mapper.MapperGeneratorSingleton;
import com.harun.accountmanagementservice.repository.AccountRepository;
import com.harun.accountmanagementservice.service.AccountService;
import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.EmailRequest;
import com.harun.common.dto.ReportDTO;
import com.harun.common.enums.ErrorMessage;
import com.harun.entity.models.Account;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private static final String ACCOUNT = "Account";
    private String message = "";

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountById(id);
        return mapper.accountToAccountDTO(account);
    }

    @Override
    public AccountDTO updateAccount(Account account) {
        Account updatedAccount = accountRepository.save(account);
        return mapper.accountToAccountDTO(updatedAccount);
    }

    @Override
    public AccountDTO saveAccount(Account account) {
        accountRepository.save(account);
        return mapper.accountToAccountDTO(account);
    }

    @Override
    public void deleteAccount(Long id) {
        try {
            accountById(id);
            accountRepository.deleteById(id);
            message = ErrorMessage.DELETION_SUCCESS.getMessage(ACCOUNT, id);
            logger.info(message);
        } catch (Exception e) {
            message = ErrorMessage.DELETION_FAILED.getMessage(ACCOUNT, id);
            logger.info(message);
        }
    }

    @Override
    public List<AccountDTO> getAccountsByUserId(Long userId) {
        List<Account> accountList = accountRepository.findByBankUser_Id(userId);
        return mapper.accountListToAccountDTOList(accountList);
    }

    private Account accountById(Long id) {
        return accountRepository.findByIdOrThrowError(id);
    }
}
