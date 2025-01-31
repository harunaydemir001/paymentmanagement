package com.harun.authservice.model;

import com.harun.common.annotation.password.ValidPassword;
import com.harun.common.base.BaseDate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserInfoEntity extends BaseDate<Long> implements Serializable {

    @Column(name = "USER_NAME")
    String userName;

    @Column(nullable = false, name = "EMAIL_ID", unique = true)
    String emailId;

    @Column(name = "MOBILE_NUMBER")
    String mobileNumber;

    @Column(nullable = false, name = "ROLES")
    String roles;

    @Column(nullable = false, name = "PASSWORD")
    @ValidPassword
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<RefreshTokenEntity> refreshTokens;
}