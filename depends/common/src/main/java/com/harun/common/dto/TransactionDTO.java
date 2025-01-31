package com.harun.common.dto;

import com.harun.common.enums.TransactionType;
import com.harun.common.models.Account;
import com.harun.common.models.Notification;
import com.harun.common.models.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    List<Notification> notifications;
}
