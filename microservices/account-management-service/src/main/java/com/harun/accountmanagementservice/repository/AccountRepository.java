package com.harun.accountmanagementservice.repository;

import com.harun.common.models.Account;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JPABaseRepository<Account, Long> {
    List<Account> findByBankUser_Id(Long userId);
}
