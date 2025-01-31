package com.harun.common.dto;

import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Account}
 */


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    Long id;

    String accountNumber;

    BigDecimal balance;

    BankUser bankUser;

    List<Transaction> outgoingTransactions = new ArrayList<>();

    List<Transaction> incomingTransactions = new ArrayList<>();
}
