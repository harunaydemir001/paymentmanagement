package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDTO extends BaseDTO<Long> {

    String cardNumber;

    LocalDate expiryDate;

    String cvv;

    Account account;

    BankUser bankUser;
}
