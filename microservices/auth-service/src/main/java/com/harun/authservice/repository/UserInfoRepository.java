package com.harun.authservice.repository;

import com.harun.authservice.model.UserInfoEntity;
import com.harun.dalcommon.repository.base.JPABaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JPABaseRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByEmailId(String emailId);
}
