package com.harun.usermanagementservice.service;

import com.harun.common.dto.BankUserDTO;
import com.harun.entity.models.BankUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

public interface BankUserService {
    BankUserDTO createUser(BankUser bankUser);

    BankUserDTO getUserById(Long id);

    Page<BankUserDTO> getAllUsers(Pageable pageable);

    BankUserDTO updateUser(BankUser bankUser);

    HttpStatus deleteUser(Long id);
}
