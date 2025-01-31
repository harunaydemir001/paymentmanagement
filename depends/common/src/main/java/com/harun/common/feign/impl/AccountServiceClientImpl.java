package com.harun.common.feign.impl;

import com.harun.common.dto.AccountDTO;
import com.harun.common.feign.client.AccountServiceClient;
import com.harun.common.response.model.Response;
import com.harun.common.utils.JsonUtil;
import com.harun.entity.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceClientImpl {

    private final AccountServiceClient accountServiceClient;

    public AccountDTO saveAccount(Account account) {
        ResponseEntity<Response> response = accountServiceClient.saveAccount(account);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), AccountDTO.class);
    }

    public AccountDTO updateAccount(Account account) {
        ResponseEntity<Response> response = accountServiceClient.updateAccount(account);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), AccountDTO.class);
    }

    public void deleteAccount(Long id) {
        accountServiceClient.deleteAccount(id);
    }

    public AccountDTO getAccountById(Long id) {
        ResponseEntity<Response> response = accountServiceClient.getAccountById(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), AccountDTO.class);
    }
}
