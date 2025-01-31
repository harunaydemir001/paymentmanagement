package com.harun.usermanagementservice.dto;

import com.harun.entity.models.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUserDTO {
    Long id;
    List<Account> accounts = new ArrayList<>();
}
