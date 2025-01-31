package com.harun.common.feign.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.common.response.model.Response;
import com.harun.entity.models.BankUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${services.user-management-service.bank-user-url}", configuration = FeignClientConfiguration.class)
public interface UserServiceClient {

    @PostMapping("/create")
    ResponseEntity<Response> createUser(@RequestBody BankUser bankUser);

    @PutMapping("/update")
    ResponseEntity<Response> updateUser(@RequestBody BankUser bankUser);

    @GetMapping("/{id}")
    ResponseEntity<Response> getUserById(@PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteUser(@PathVariable("id") Long id);

    @GetMapping("/getAllUsers")
    ResponseEntity<Response> getAllUsers();
}
