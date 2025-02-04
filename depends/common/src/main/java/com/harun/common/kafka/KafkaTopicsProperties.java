package com.harun.common.kafka;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.topic")
@RefreshScope
public class KafkaTopicsProperties {

    @Getter
    private static String emailTopic;
    @Getter
    private static String reportTopic;

    @Value("${kafka.topic.email}")
    private String emailInstance;

    @Value("${kafka.topic.report}")
    private String reportInstance;

    @PostConstruct
    private void init() {
        emailTopic = emailInstance;
        reportTopic = reportInstance;
    }
}
