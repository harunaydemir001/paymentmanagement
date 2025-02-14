package com.harun.paymentprocessingservice.service;

import com.harun.common.dto.PaymentDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.dto.PaymentSagaState;
import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.model.PayRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentService {

    PaymentDTO getPaymentById(Long id);

    PaymentDTO updatePayment(Payment payment);

    PaymentDTO savePayment(Payment payment);

    void deletePayment(Long id);

    PaymentSagaState processPayment(PaymentRequest paymentRequest);

    PaymentDTO payInvoice(Double amount);

    Page<PaymentDTO> filter(Pageable pageable, PaymentDTO paymentDTO);

    void pay(PayRequest payRequest);

    void processBatch(List<Payment> payments);
}
