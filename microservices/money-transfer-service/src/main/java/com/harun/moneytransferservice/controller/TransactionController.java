package com.harun.moneytransferservice.controller;


import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.entity.models.Transaction;
import com.harun.moneytransferservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getTransactionById(@PathVariable Long id) {
        return ResponseFactory.createResponse(transactionService.getTransactionById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> saveTransaction(@RequestBody Transaction transaction) {
        return ResponseFactory.createResponse(transactionService.saveTransaction(transaction), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Response> updateTransaction(@RequestBody Transaction transaction) {
        return ResponseFactory.createResponse(transactionService.updateTransaction(transaction), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
        return ResponseFactory.createSuccessResponse();
    }
}