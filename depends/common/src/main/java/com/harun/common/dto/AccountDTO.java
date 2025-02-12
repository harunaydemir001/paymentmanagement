package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.enums.AccountType;
import com.harun.entity.models.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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

    Long bankUserId;

    Integer version;
}
