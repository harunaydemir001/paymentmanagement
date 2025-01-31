package com.harun.paymentprocessingservice.repository;

import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JPABaseRepository<Payment, Long> {
}
