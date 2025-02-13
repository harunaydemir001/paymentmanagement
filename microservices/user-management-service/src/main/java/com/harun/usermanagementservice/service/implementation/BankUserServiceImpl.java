package com.harun.usermanagementservice.service.implementation;

import com.harun.common.dto.BankUserDTO;
import com.harun.entity.models.BankUser;
import com.harun.usermanagementservice.mapper.MapperGenerator;
import com.harun.usermanagementservice.mapper.MapperGeneratorSingleton;
import com.harun.usermanagementservice.mapper.PageMapper;
import com.harun.usermanagementservice.repository.BankUserRepository;
import com.harun.usermanagementservice.service.BankUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserServiceImpl implements BankUserService {

    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final BankUserRepository bankUserRepository;

    public BankUserServiceImpl(BankUserRepository bankUserRepository) {
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
    public Page<BankUserDTO> getAllUsers(Pageable pageable) {
        Page<BankUser> userPage = bankUserRepository.findAll(pageable);
        List<BankUserDTO> bankUserDTOS = mapper.userToUserDTO(userPage.getContent());
        return new PageImpl<>(bankUserDTOS, pageable, userPage.getTotalElements());
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

    @Override
    public Page<BankUserDTO> filter(Pageable pageable, BankUserDTO bankUserDTO) {
        Page<BankUser> page = bankUserRepository.findByFilter(pageable, bankUserDTO);
        List<BankUserDTO> directorDTOList = mapper.userToUserDTO(page.getContent());
        return PageMapper.toPage(page, directorDTOList);
    }
}
