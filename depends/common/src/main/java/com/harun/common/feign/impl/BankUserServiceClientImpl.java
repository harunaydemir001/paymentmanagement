package com.harun.common.feign.impl;

import com.harun.common.dto.BankUserDTO;
import com.harun.common.feign.client.BankUserServiceClient;
import com.harun.common.response.model.Response;
import com.harun.common.utils.JsonUtil;
import com.harun.entity.models.BankUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BankUserServiceClientImpl {
    private final BankUserServiceClient bankUserServiceClient;

    public BankUserDTO saveBankUser(BankUser bankUser) {
        ResponseEntity<Response> response = bankUserServiceClient.createUser(bankUser);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), BankUserDTO.class);
    }

    public BankUserDTO updateBankUser(BankUser bankUser) {
        ResponseEntity<Response> response = bankUserServiceClient.updateUser(bankUser);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), BankUserDTO.class);
    }

    public void deleteBankUser(Long id) {
        bankUserServiceClient.deleteUser(id);
    }

    public BankUserDTO getBankUserById(Long id) {
        ResponseEntity<Response> response = bankUserServiceClient.getUserById(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), BankUserDTO.class);
    }

    public List<BankUserDTO> getAllUsers() {
        ResponseEntity<Response> response = bankUserServiceClient.getAllUsers();
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), List.class);
    }
}
