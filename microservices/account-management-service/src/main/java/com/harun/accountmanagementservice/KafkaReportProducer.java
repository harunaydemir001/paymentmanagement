package com.harun.accountmanagementservice;

import com.harun.common.dto.ReportDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaReportProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaReportProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReport(ReportDTO reportDTO) {
        kafkaTemplate.send("report-topic", reportDTO);
    }
}