package com.harun.moneytransferservice.repository;

import com.harun.common.models.Transaction;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JPABaseRepository<Transaction, Long> {
}
