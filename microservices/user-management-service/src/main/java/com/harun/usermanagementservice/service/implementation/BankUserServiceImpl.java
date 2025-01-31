package com.harun.usermanagementservice.service.implementation;

import com.harun.entity.models.BankUser;
import com.harun.usermanagementservice.dto.BankUserDTO;
import com.harun.usermanagementservice.mapper.MapperGenerator;
import com.harun.usermanagementservice.mapper.MapperGeneratorSingleton;
import com.harun.usermanagementservice.repository.BankUserRepositoryJPA;
import com.harun.usermanagementservice.service.BankUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserServiceImpl implements BankUserService {

    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final BankUserRepositoryJPA bankUserRepository;

    public BankUserServiceImpl(BankUserRepositoryJPA bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public BankUserDTO createUser(BankUser bankUser) {
        bankUserRepository.save(bankUser);
        return mapper.userToUserDTO(bankUser);
    }

    @Override
    public BankUserDTO getUserById(Long id) {
        BankUser bankUser = bankUserRepository.findByIdOrThrowError(id);
        return mapper.userToUserDTO(bankUser);
    }

    @Override
    public List<BankUserDTO> getAllUsers() {
        final List<BankUser> allBankUsers = bankUserRepository.findAll();
        return mapper.UserListToUserDTOList(allBankUsers);
    }

    @Override
    public BankUserDTO updateUser(BankUser bankUser) {
        final BankUser updatedBankUser = bankUserRepository.save(bankUser);
        return mapper.userToUserDTO(updatedBankUser);
    }

    @Override
    public HttpStatus deleteUser(Long id) {
        bankUserRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
