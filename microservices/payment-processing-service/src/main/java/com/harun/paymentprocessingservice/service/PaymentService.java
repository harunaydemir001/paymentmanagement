package com.harun.paymentprocessingservice.service;

import com.harun.common.models.Payment;
import com.harun.paymentprocessingservice.dto.PaymentDTO;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentDTO getPaymentById(Long id);

    PaymentDTO updatePayment(Payment payment);

    PaymentDTO savePayment(Payment payment);

    void deletePayment(Long id);

    void validateTransferInputs(Long sourceAccountId, Long targetAccountId, BigDecimal amount);
}
