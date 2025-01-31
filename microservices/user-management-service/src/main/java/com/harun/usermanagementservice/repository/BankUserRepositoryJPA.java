package com.harun.usermanagementservice.repository;

import com.harun.common.models.BankUser;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepositoryJPA extends JPABaseRepository<BankUser, Long> {
}
