package com.harun.usermanagementservice.service;

import com.harun.usermanagementservice.dto.BankUserDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface BankUser {
    BankUserDTO createUser(com.harun.common.models.BankUser bankUser);
    BankUserDTO getUserById(Long id);
    List<BankUserDTO> getAllUsers();
    BankUserDTO updateUser(com.harun.common.models.BankUser bankUser);
    HttpStatus deleteUser(Long id);
}
