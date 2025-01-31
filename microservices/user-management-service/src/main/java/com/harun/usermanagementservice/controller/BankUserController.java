package com.harun.usermanagementservice.controller;

import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.usermanagementservice.service.BankUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bank-user")
@RequiredArgsConstructor
@Validated
public class BankUserController {

    private final BankUser bankUser;


    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody com.harun.common.models.BankUser bankUser) {
        return ResponseFactory.createResponse(this.bankUser.createUser(bankUser), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody com.harun.common.models.BankUser bankUser) {
        return ResponseFactory.createResponse(this.bankUser.updateUser(bankUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAllUsers(@PathVariable Long id) {
        return ResponseFactory.createResponse(bankUser.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        return ResponseFactory.createResponse(bankUser.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Response> getAllUsers() {
        return ResponseFactory.createResponse(bankUser.getAllUsers(), HttpStatus.OK);
    }
}
