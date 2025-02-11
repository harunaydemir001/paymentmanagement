package com.harun.accountmanagementservice.controller;

import com.harun.accountmanagementservice.service.AccountService;
import com.harun.common.dto.AccountDTO;
import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.entity.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/account")
@Validated
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAccountById(@PathVariable Long id) {
        return ResponseFactory.createResponse(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> saveAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseFactory.createResponse(accountService.saveAccount(accountDTO), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Response> updateAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseFactory.createResponse(accountService.updateAccount(accountDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseFactory.createSuccessResponse();
    }
}
