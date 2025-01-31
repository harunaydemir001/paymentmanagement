package com.harun.paymentprocessingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.harun.common.feign")
@EnableFeignClients(basePackages = "com.harun.common.feign")
@EntityScan("com.harun.entity.models")
public class PaymentProcessingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessingServiceApplication.class, args);
    }

}
