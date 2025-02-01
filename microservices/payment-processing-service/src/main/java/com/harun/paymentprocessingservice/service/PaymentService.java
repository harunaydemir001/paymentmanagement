package com.harun.paymentprocessingservice.service;

import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.dto.PaymentDTO;
import com.harun.paymentprocessingservice.dto.PaymentRequest;
import com.harun.paymentprocessingservice.model.PaymentSagaState;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentDTO getPaymentById(Long id);

    PaymentDTO updatePayment(Payment payment);

    PaymentDTO savePayment(Payment payment);

    void deletePayment(Long id);

     PaymentSagaState processPayment(PaymentRequest paymentRequest);
}
