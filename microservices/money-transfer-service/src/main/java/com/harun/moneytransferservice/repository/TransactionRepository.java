package com.harun.moneytransferservice.repository;

import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JPABaseRepository<Transaction, Long> {
}
