package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.enums.AccountType;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Account}
 */


@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AccountDTO extends BaseDTO<Long> {

    AccountType accountType;

    BigDecimal balance;

    String iban;

    BankUser bankUser;

    List<Long> transactionIds = new ArrayList<>();
}
