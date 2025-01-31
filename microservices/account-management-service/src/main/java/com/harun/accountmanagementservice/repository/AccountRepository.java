package com.harun.accountmanagementservice.repository;

import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JPABaseRepository<Account, Long> {
    List<Account> findByBankUser_Id(Long userId);
}
