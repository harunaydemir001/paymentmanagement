package com.harun.common.feign.impl;

import com.harun.common.dto.PaymentDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.dto.PaymentSagaState;
import com.harun.common.feign.client.PaymentServiceClient;
import com.harun.common.response.model.Response;
import com.harun.common.utils.JsonUtil;
import com.harun.entity.models.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentServiceClientImpl {
    private final PaymentServiceClient paymentServiceClient;

    public PaymentDTO savePayment(Payment payment) {
        ResponseEntity<Response> response = paymentServiceClient.savePayment(payment);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PaymentDTO.class);
    }

    public PaymentDTO updatePayment(Payment payment) {
        ResponseEntity<Response> response = paymentServiceClient.updatePayment(payment);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PaymentDTO.class);
    }

    public void deletePayment(Long id) {
        paymentServiceClient.deletePayment(id);
    }

    public PaymentDTO getPaymentById(Long id) {
        ResponseEntity<Response> response = paymentServiceClient.getPaymentById(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PaymentDTO.class);
    }

    public PaymentSagaState processPayment(PaymentRequest paymentRequest) {
        ResponseEntity<Response> response = paymentServiceClient.processPayment(paymentRequest);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PaymentSagaState.class);
    }

    public PaymentDTO payInvoice(Double amount) {
        ResponseEntity<Response> response = paymentServiceClient.payInvoice(amount);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PaymentDTO.class);
    }
}