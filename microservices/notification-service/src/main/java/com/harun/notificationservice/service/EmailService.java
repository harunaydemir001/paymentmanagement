package com.harun.notificationservice.service;


import com.harun.common.dto.EmailRequest;

public interface EmailService {

    String sendSimpleMail(EmailRequest emailRequest);

    String sendMailWithAttachment(EmailRequest emailRequest);
}
