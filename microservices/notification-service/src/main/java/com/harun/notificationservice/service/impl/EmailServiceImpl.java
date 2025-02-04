package com.harun.notificationservice.service.impl;


import com.harun.common.dto.EmailRequest;
import com.harun.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
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

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailRequest emailRequest) {

        // Try block to check for exceptions
//        try {

//            // Creating a simple mail message
//            SimpleMailMessage mailMessage
//                    = new SimpleMailMessage();
//
//            // Setting up necessary emailRequest
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(emailRequest.getRecipient());
//            mailMessage.setText(emailRequest.getMsgBody());
//            mailMessage.setSubject(emailRequest.getSubject());
//
//            // Sending the mail
//            javaMailSender.send(mailMessage);
//            return "Mail Sent Successfully...";
//        }
//
//        // Catch block to handle the exceptions
//        catch (Exception e) {
//            return "Error while Sending Mail";
//        }
//    }

        try {
            // Creating a task that sends the email
            Callable<String> emailTask = () -> {
                // Creating a simple mail message
                SimpleMailMessage mailMessage = new SimpleMailMessage();

                // Setting up necessary emailRequest
                mailMessage.setFrom(sender);
                mailMessage.setTo(emailRequest.getRecipient());
                mailMessage.setText(emailRequest.getMsgBody());
                mailMessage.setSubject(emailRequest.getSubject());

                // Sending the mail
                javaMailSender.send(mailMessage);
                return "Mail Sent Successfully...";
            };

            // Create an executor service with a single thread
            ExecutorService executor = Executors.newSingleThreadExecutor();

            // Submit the task for execution with a timeout of 20 seconds
            Future<String> future = executor.submit(emailTask);

            // Get the result with a timeout of 20 seconds
            return future.get(20, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            System.out.println("Error while Sending Mail: Timeout after 20 seconds");
            return "Error while Sending Mail: Timeout after 20 seconds.";
        } catch (Exception e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }


    public String sendMailWithAttachment(EmailRequest details) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
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