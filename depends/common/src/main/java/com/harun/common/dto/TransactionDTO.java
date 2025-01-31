package com.harun.common.dto;


import com.harun.entity.enums.TransactionType;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link Transaction}
 */

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class TransactionDTO {
    Long id;

    BigDecimal amount;

    LocalDateTime transactionDate;

    TransactionType transactionType;

    Account fromAccount;

    Account toAccount;

    BankUser bankUser;
}
