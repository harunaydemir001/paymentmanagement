package com.harun.accountmanagementservice;

import com.harun.common.dto.EmailRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEmailProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmailRequest(EmailRequest emailRequest) {
        kafkaTemplate.send("email-topic", emailRequest);
    }
}