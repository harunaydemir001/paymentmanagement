package com.harun.common.feign.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.response.model.Response;
import com.harun.entity.models.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment-processing-service", url = "${services.payment-processing-service.payment-url}", configuration = FeignClientConfiguration.class)
public interface PaymentServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<Response> getPaymentById(@PathVariable Long id);

    @PostMapping("/create")
    ResponseEntity<Response> savePayment(@RequestBody Payment payment);

    @PutMapping("/update")
    ResponseEntity<Response> updatePayment(@RequestBody Payment payment);

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deletePayment(@PathVariable("id") Long id);

    @PostMapping("/process-payment")
    ResponseEntity<Response> processPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("/pay-invoice")
    ResponseEntity<Response> payInvoice(@RequestParam Double amount);
}
