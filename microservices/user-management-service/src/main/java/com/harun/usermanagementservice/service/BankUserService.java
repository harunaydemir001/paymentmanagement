package com.harun.usermanagementservice.service;

import com.harun.entity.models.BankUser;
import com.harun.usermanagementservice.dto.BankUserDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface BankUserService {
    BankUserDTO createUser(BankUser bankUser);
    BankUserDTO getUserById(Long id);
    List<BankUserDTO> getAllUsers();
    BankUserDTO updateUser(BankUser bankUser);
    HttpStatus deleteUser(Long id);
}
