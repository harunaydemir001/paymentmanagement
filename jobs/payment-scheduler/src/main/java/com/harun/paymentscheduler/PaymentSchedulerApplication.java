package com.harun.paymentscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.harun.common", "com.harun.paymentprocessingservice", "com.harun.paymentscheduler"})
public class PaymentSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSchedulerApplication.class, args);
    }

}
