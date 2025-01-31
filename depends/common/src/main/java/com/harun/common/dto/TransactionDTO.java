package com.harun.common.dto;


import com.harun.entity.enums.TransactionType;
import com.harun.entity.models.Account;
import com.harun.entity.models.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link Transaction}
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    BigDecimal amount;

    LocalDateTime timestamp;

    TransactionType type;

    Account sourceAccount;

    Account targetAccount;
}
