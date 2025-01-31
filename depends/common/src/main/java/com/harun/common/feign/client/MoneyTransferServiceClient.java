package com.harun.common.feign.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.common.response.model.Response;
import com.harun.entity.models.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "money-transfer-service", url = "${services.money-transfer-service.transaction-url}", configuration = FeignClientConfiguration.class)
public interface MoneyTransferServiceClient {


    @GetMapping("/{id}")
    ResponseEntity<Response> getTransactionById(@PathVariable Long id);

    @PostMapping("/create")
    ResponseEntity<Response> saveTransaction(@RequestBody Transaction transaction);

    @PutMapping("/update")
    ResponseEntity<Response> updateTransaction(@RequestBody Transaction transaction);

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteTransaction(@PathVariable("id") Long id);

    @GetMapping("/money-transfer")
    ResponseEntity<Response> moneyTransfer(@RequestParam Long sourceAccountId, @RequestParam Long targetAccountId, @RequestParam BigDecimal amount);
}
