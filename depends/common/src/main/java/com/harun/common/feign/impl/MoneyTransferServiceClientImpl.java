package com.harun.common.feign.impl;

import com.harun.common.dto.TransactionDTO;
import com.harun.common.feign.client.MoneyTransferServiceClient;
import com.harun.common.models.Transaction;
import com.harun.common.response.model.Response;
import com.harun.common.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MoneyTransferServiceClientImpl {

    private final MoneyTransferServiceClient moneyTransferServiceClient;

    public TransactionDTO saveTransaction(Transaction transaction) {
        ResponseEntity<Response> response = moneyTransferServiceClient.saveTransaction(transaction);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), TransactionDTO.class);
    }

    public TransactionDTO updateTransaction(Transaction transaction) {
        ResponseEntity<Response> response = moneyTransferServiceClient.updateTransaction(transaction);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), TransactionDTO.class);
    }

    public void deleteTransaction(Long id) {
        moneyTransferServiceClient.deleteTransaction(id);
    }

    public TransactionDTO getTransactionById(Long id) {
        ResponseEntity<Response> response = moneyTransferServiceClient.getTransactionById(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), TransactionDTO.class);
    }
}
