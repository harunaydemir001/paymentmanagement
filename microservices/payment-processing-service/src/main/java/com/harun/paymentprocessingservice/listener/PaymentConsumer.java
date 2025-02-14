package com.harun.paymentprocessingservice.listener;

import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentService paymentService;
    private final List<Payment> paymentBatch = new ArrayList<>();
    private static final int BATCH_SIZE = 1000;


    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void consume(Payment payment) {
        paymentBatch.add(payment);

        if (paymentBatch.size() >= BATCH_SIZE) {
            paymentService.processBatch(paymentBatch);
        }
    }

    public void insertData(){
        paymentService.processBatch(paymentBatch);
    }

}
