package com.harun.common.utils;

import com.harun.common.dto.ReportDTO;
import com.harun.common.enums.EventType;
import com.harun.common.kafka.KafkaProducer;
import com.harun.common.kafka.KafkaTopicsProperties;

import java.util.Map;

public class ReportUtil {
    private ReportUtil() {
    }

    public static void createReport(Long userId, EventType eventType, String message, Map<String, Object> metadata) {
        ReportDTO reportDTO = createReportDTO(userId, eventType, message, metadata);
        KafkaProducer.sendKafkaMessage(reportDTO, KafkaTopicsProperties.getReportTopic());
    }

    private static ReportDTO createReportDTO(Long userId, EventType eventType, String message, Map<String, Object> metadata) {
        return ReportDTO.builder()
                .withUserId(userId)
                .withEventType(eventType)
                .withMessage(message)
                .withMetadata(metadata)
                .build();
    }
}