package com.harun.usermanagementservice.mapper;



import com.harun.entity.models.BankUser;
import com.harun.common.dto.BankUserDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {
    BankUserDTO userToUserDTO(BankUser bankUser);

    List<BankUserDTO> UserListToUserDTOList(List<BankUser> allBankUsers);
}