package com.harun.authservice.repository;


import com.harun.authservice.model.RefreshTokenEntity;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends JPABaseRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

}