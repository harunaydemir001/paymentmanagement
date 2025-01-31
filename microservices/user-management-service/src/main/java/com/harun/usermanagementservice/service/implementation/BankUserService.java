package com.harun.usermanagementservice.service.implementation;

import com.harun.usermanagementservice.dto.BankUserDTO;
import com.harun.usermanagementservice.mapper.MapperGenerator;
import com.harun.usermanagementservice.mapper.MapperGeneratorSingleton;
import com.harun.usermanagementservice.repository.BankUserRepositoryJPA;
import com.harun.usermanagementservice.service.BankUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserService implements BankUser {

    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final BankUserRepositoryJPA bankUserRepository;

    public BankUserService(BankUserRepositoryJPA bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public BankUserDTO createUser(com.harun.common.models.BankUser bankUser) {
        bankUserRepository.save(bankUser);
        return mapper.userToUserDTO(bankUser);
    }

    @Override
    public BankUserDTO getUserById(Long id) {
        com.harun.common.models.BankUser bankUser = bankUserRepository.findByIdOrThrowError(id);
        return mapper.userToUserDTO(bankUser);
    }

    @Override
    public List<BankUserDTO> getAllUsers() {
        final List<com.harun.common.models.BankUser> allBankUsers = bankUserRepository.findAll();
        return mapper.UserListToUserDTOList(allBankUsers);
    }

    @Override
    public BankUserDTO updateUser(com.harun.common.models.BankUser bankUser) {
        final com.harun.common.models.BankUser updatedBankUser = bankUserRepository.save(bankUser);
        return mapper.userToUserDTO(updatedBankUser);
    }

    @Override
    public HttpStatus deleteUser(Long id) {
        bankUserRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
