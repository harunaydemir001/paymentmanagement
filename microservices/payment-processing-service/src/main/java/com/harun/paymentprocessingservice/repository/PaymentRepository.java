package com.harun.paymentprocessingservice.repository;

import com.harun.common.models.Payment;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JPABaseRepository<Payment, Long> {
}
