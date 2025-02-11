package com.harun.accountmanagementservice.service.impl;

import com.harun.accountmanagementservice.mapper.MapperGenerator;
import com.harun.accountmanagementservice.mapper.MapperGeneratorSingleton;
import com.harun.accountmanagementservice.repository.AccountRepository;
import com.harun.accountmanagementservice.service.AccountService;
import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.BankUserDTO;
import com.harun.common.enums.ErrorMessage;
import com.harun.common.feign.impl.BankUserServiceClientImpl;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private static final String ACCOUNT = "Account";
    private String message = "";

    private final AccountRepository accountRepository;
    private final BankUserServiceClientImpl bankUserServiceClient;

    @Override
//    @Cacheable(value = "account", key = "#id")
    public AccountDTO getAccountById(Long id) {
        Account account = accountById(id);
        return mapper.accountToAccountDTO(account);
    }

    @Override
//    @CachePut(value = "account", key = "#result.id")
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        BankUserDTO bankUserDTO = bankUserServiceClient.getBankUserById(accountDTO.getId());
        BankUser bankUser = mapper.bankUserDTOToBankUser(bankUserDTO);
        Account account = mapper.accountDTOToAccount(accountDTO);
        account.setBankUser(bankUser);
        Account updatedAccount = accountRepository.save(account);
        return mapper.accountToAccountDTO(updatedAccount);
    }

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        BankUserDTO bankUserDTO = bankUserServiceClient.getBankUserById(accountDTO.getId());
        BankUser bankUser = mapper.bankUserDTOToBankUser(bankUserDTO);
        Account account = mapper.accountDTOToAccount(accountDTO);
        account.setBankUser(bankUser);
        Account savedAccount = accountRepository.save(account);
        return mapper.accountToAccountDTO(savedAccount);
    }

    @Override
//    @CacheEvict(value = "account", key = "#id")
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

    private Account accountById(Long id) {
        return accountRepository.findByIdOrThrowError(id);
    }
}
