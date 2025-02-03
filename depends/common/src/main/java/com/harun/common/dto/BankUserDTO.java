package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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

    List<Long> accountIds = new ArrayList<>();
}
