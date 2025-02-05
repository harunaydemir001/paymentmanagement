package com.harun.notificationservice.service.impl;


import com.harun.common.dto.EmailRequest;
import com.harun.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailRequest emailRequest) {

        try {

            Callable<String> emailTask = () -> {

                SimpleMailMessage mailMessage = new SimpleMailMessage();

                mailMessage.setFrom(sender);
                mailMessage.setTo(emailRequest.getRecipient());
                mailMessage.setText(emailRequest.getMsgBody());
                mailMessage.setSubject(emailRequest.getSubject());

                javaMailSender.send(mailMessage);
                return "Mail Sent Successfully...";
            };

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<String> future = executor.submit(emailTask);

            return future.get(20, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            String message = "Error while Sending Mail: Timeout after 20 seconds.";
            logger.info(message);
            return message;
        } catch (Exception e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }


    public String sendMailWithAttachment(EmailRequest details) {

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}