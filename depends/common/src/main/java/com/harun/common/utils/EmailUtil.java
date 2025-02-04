package com.harun.common.utils;

import com.harun.common.dto.EmailRequest;
import com.harun.common.kafka.KafkaProducer;
import com.harun.common.kafka.KafkaTopicsProperties;

public class EmailUtil {

    private EmailUtil() {
    }

    public static void sendEmail(String recipient, String message, String subject, String attachments) {
        EmailRequest emailRequest = createEmailRequest(recipient, message, subject, attachments);
        KafkaProducer.sendKafkaMessage(emailRequest, KafkaTopicsProperties.getEmailTopic());
    }

    private static EmailRequest createEmailRequest(String recipient, String message, String subject, String attachments) {
        return EmailRequest.builder()
                .withRecipient(recipient)
                .withMsgBody(StringBuilderUtil.buildMessage(message))
                .withSubject(subject)
                .withAttachment(attachments)
                .build();
    }
}
