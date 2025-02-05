package com.harun.common.factory;

import com.harun.common.dto.EmailRequest;
import com.harun.common.dto.ReportDTO;
import com.harun.common.enums.EventType;
import com.harun.common.utils.StringBuilderUtil;

import java.util.Map;


public class DTOFactory {

    private DTOFactory() {
    }


    public static ReportDTO createReportDTO(Long userId, EventType eventType, String message, Map<String, Object> metadata) {
        return ReportDTO.builder()
                .withUserId(userId)
                .withEventType(eventType)
                .withMessage(message)
                .withMetadata(metadata)
                .build();
    }

    public static EmailRequest createEmailRequest(String recipient, String message, String subject, String attachments) {
        return EmailRequest.builder()
                .withRecipient(recipient)
                .withMsgBody(StringBuilderUtil.buildMessage(message))
                .withSubject(subject)
                .withAttachment(attachments)
                .build();
    }
}