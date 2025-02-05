package com.harun.paymentprocessingservice.service;

import com.harun.common.dto.PaymentDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.dto.PaymentSagaState;
import com.harun.entity.models.Payment;

public interface PaymentService {

    PaymentDTO getPaymentById(Long id);

    PaymentDTO updatePayment(Payment payment);

    PaymentDTO savePayment(Payment payment);

    void deletePayment(Long id);

    PaymentSagaState processPayment(PaymentRequest paymentRequest);

    PaymentDTO payInvoice(Double amount);
}
