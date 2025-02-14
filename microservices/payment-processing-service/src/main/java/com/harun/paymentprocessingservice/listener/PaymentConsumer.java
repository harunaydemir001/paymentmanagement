package com.harun.paymentprocessingservice.listener;

import com.harun.entity.models.Payment;
import com.harun.paymentprocessingservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentService paymentService;
    private final List<Payment> paymentBatch = new ArrayList<>();
    private static final int BATCH_SIZE = 1000;


    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void consume(Payment payment) {
        synchronized (paymentBatch) {
            paymentBatch.add(payment);

            if (paymentBatch.size() >= BATCH_SIZE) {
                processAndClear();
            }
        }
    }

    public void insertData() {
        synchronized (paymentBatch) {
            processAndClear();
        }
    }

    private void processAndClear() {
        if (!paymentBatch.isEmpty()) {
            paymentService.processBatch(new ArrayList<>(paymentBatch));
            paymentBatch.clear();
        }
    }

}
