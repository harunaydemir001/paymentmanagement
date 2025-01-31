package com.harun.notificationservice.controller;


import com.harun.common.dto.EmailRequest;
import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<Response> sendMail(@RequestBody EmailRequest emailRequest) {
        return ResponseFactory.createResponse(emailService.sendSimpleMail(emailRequest), HttpStatus.OK);
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<Response> sendMailWithAttachment(@RequestBody EmailRequest emailRequest) {
        return ResponseFactory.createResponse(emailService.sendMailWithAttachment(emailRequest), HttpStatus.OK);
    }
}