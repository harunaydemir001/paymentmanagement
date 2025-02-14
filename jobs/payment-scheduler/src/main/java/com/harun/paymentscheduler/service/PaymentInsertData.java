package com.harun.paymentscheduler.service;

import com.harun.paymentprocessingservice.listener.PaymentConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInsertData {
    private final PaymentConsumer paymentConsumer;

    public void insertData() {
        paymentConsumer.insertData();
    }
}

