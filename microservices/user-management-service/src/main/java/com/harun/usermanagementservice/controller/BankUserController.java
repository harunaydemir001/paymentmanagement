package com.harun.usermanagementservice.controller;

import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.entity.models.BankUser;
import com.harun.usermanagementservice.service.BankUserService;
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

    private final BankUserService bankUserService;


    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody BankUser bankUser) {
        return ResponseFactory.createResponse(bankUserService.createUser(bankUser), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody BankUser bankUser) {
        return ResponseFactory.createResponse(bankUserService.updateUser(bankUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAllUsers(@PathVariable Long id) {
        return ResponseFactory.createResponse(bankUserService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        return ResponseFactory.createResponse(bankUserService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Response> getAllUsers() {
        return ResponseFactory.createResponse(bankUserService.getAllUsers(), HttpStatus.OK);
    }
}
