package com.harun.accountmanagementservice.controller;

import com.harun.accountmanagementservice.service.AccountService;
import com.harun.common.models.Account;
import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
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
    public ResponseEntity<Response> saveAccount(@RequestBody Account account) {
        return ResponseFactory.createResponse(accountService.saveAccount(account), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Response> updateAccount(@RequestBody Account account) {
        return ResponseFactory.createResponse(accountService.updateAccount(account), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseFactory.createSuccessResponse();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Response> getAccountsByUserId(@PathVariable("userId") Long userId) {
        return ResponseFactory.createResponse(accountService.getAccountsByUserId(userId), HttpStatus.OK);
    }
}
