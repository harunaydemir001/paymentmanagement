package com.harun.paymentprocessingservice.controller;

import com.harun.common.dto.PaymentDTO;
import com.harun.common.dto.PaymentRequest;
import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPaymentById(@PathVariable Long id) {
        return ResponseFactory.createResponse(paymentService.getPaymentById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> savePayment(@RequestBody Payment payment) {
        return ResponseFactory.createResponse(paymentService.savePayment(payment), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updatePayment(@RequestBody Payment payment) {
        return ResponseFactory.createResponse(paymentService.updatePayment(payment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePayment(@PathVariable("id") Long id) {
        paymentService.deletePayment(id);
        return ResponseFactory.createSuccessResponse();
    }

    @PostMapping("/process-payment")
    public ResponseEntity<Response> processPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseFactory.createResponse(paymentService.processPayment(paymentRequest), HttpStatus.OK);
    }

    @PostMapping("/pay-invoice")
    public ResponseEntity<Response> payInvoice(@RequestParam Double amount) {
        return ResponseFactory.createResponse(paymentService.payInvoice(amount), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<Response> filter(@ParameterObject Pageable pageable,
                                           @RequestBody() PaymentDTO paymentDTO) {
        return ResponseFactory.createResponse(paymentService.filter(pageable, paymentDTO), HttpStatus.OK);
    }
}
