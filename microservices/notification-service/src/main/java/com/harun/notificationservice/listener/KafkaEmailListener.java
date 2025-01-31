package com.harun.notificationservice.listener;

import com.harun.common.dto.EmailRequest;
import com.harun.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEmailListener {

    private final EmailService emailService;


    @KafkaListener(topics = "email-topic", groupId = "email-group")
    public void listen(EmailRequest emailRequest) {
        if (emailRequest.getAttachment() != null && !emailRequest.getAttachment().isEmpty()) {
            emailService.sendMailWithAttachment(emailRequest);
        } else {
            emailService.sendSimpleMail(emailRequest);
        }
    }
}