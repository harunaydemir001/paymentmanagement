package com.harun.accountmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EntityScan("com.harun.entity.models")
@EnableFeignClients(basePackages = "com.harun.common.feign")
@EnableCaching
@ComponentScan(basePackages = {"com.harun.common.feign", "com.harun.accountmanagementservice"})
public class AccountManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementServiceApplication.class, args);
    }

}
