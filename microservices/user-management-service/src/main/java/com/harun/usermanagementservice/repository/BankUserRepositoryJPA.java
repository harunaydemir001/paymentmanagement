package com.harun.usermanagementservice.repository;


import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.BankUser;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepositoryJPA extends JPABaseRepository<BankUser, Long> {
}
