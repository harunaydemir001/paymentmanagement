package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.enums.Gender;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Campaign;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link BankUser}
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUserDTO extends BaseDTO<Long> {

    String email;

    String firstName;

    String lastName;

    String phoneNumber;

    Gender gender;

    boolean isEmailVerified = false;

    boolean isPhoneVerified = false;

    String occupation;

    BigDecimal monthlyIncome;

    Integer creditScore;

    Set<Account> accounts = new HashSet<>();

    Set<Campaign> campaigns = new HashSet<>();
}
