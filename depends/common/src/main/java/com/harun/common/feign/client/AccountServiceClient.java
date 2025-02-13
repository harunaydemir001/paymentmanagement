package com.harun.common.feign.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.common.dto.AccountDTO;
import com.harun.common.response.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service", url = "${services.account-management-service.account-url}", configuration = FeignClientConfiguration.class)
public interface AccountServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<Response> getAccountById(@PathVariable("id") Long id);

    @PostMapping("/create")
    ResponseEntity<Response> saveAccount(@RequestBody AccountDTO accountDTO);

    @PutMapping("/update")
    ResponseEntity<Response> updateAccount(@RequestBody AccountDTO accountDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteAccount(@PathVariable("id") Long id);
}
