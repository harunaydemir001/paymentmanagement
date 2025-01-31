package com.harun.authservice.config.user;

import com.harun.authservice.model.UserInfoEntity;
import com.harun.authservice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class InitialUserInfo implements CommandLineRunner {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var manager = UserInfoEntity.builder()
                .withUserName("Manager")
                .withPassword(passwordEncoder.encode("password"))
                .withRoles("ROLE_MANAGER")
                .withEmailId("manager@manager.com")
                .build();

        var admin = UserInfoEntity.builder()
                .withUserName("Admin")
                .withPassword(passwordEncoder.encode("password"))
                .withRoles("ROLE_ADMIN")
                .withEmailId("admin@admin.com")
                .build();


        var user = UserInfoEntity.builder()
                .withUserName("User")
                .withPassword(passwordEncoder.encode("password"))
                .withRoles("ROLE_USER")
                .withEmailId("user@user.com")
                .build();

        userInfoRepository.saveAll(List.of(manager, admin, user));
    }
}