package com.harun.common.kafka;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "kafka.topic")
public class KafkaTopicsProperties {
    private Map<String, String> topics;
    private static KafkaTopicsProperties instance;

    @PostConstruct
    private void init() {
        instance = this;
    }

    public Map<String, String> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, String> topics) {
        this.topics = topics;
    }

    public static String getEmailTopic() {
        return instance.topics.get("email");
    }

    public static String getReportTopic() {
        return instance.topics.get("report");
    }
}
