package com.harun.common.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        KafkaProducer.kafkaTemplate = kafkaTemplate;
    }

    public static void sendKafkaMessage(Object data, String topic) {
        kafkaTemplate.send(topic, data);
    }
}