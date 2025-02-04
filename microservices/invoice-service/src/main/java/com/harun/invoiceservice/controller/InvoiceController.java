package com.harun.invoiceservice.controller;

import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.entity.enums.InvoiceType;
import com.harun.entity.models.Invoice;
import com.harun.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@Validated
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getInvoiceById(@PathVariable Long id) {
        return ResponseFactory.createResponse(invoiceService.getInvoiceById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> saveInvoice(@RequestBody Invoice invoice) {
        return ResponseFactory.createResponse(invoiceService.saveInvoice(invoice), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Response> updateInvoice(@RequestBody Invoice invoice) {
        return ResponseFactory.createResponse(invoiceService.updateInvoice(invoice), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseFactory.createSuccessResponse();
    }

    @GetMapping("/pay-invoice")
    public ResponseEntity<Response> payInvoice(InvoiceType invoiceType, Double amount) {
        invoiceService.payInvoice(invoiceType, amount);
        return ResponseFactory.createSuccessResponse();
    }
}
