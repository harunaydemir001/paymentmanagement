package com.harun.authservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REFRESH_TOKENS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "REFRESH_TOKEN", nullable = false, length = 10000)
    String refreshToken;

    @Column(name = "REVOKED")
    boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserInfoEntity user;

}