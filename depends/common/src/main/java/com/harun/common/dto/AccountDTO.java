package com.harun.common.dto;

import com.harun.entity.enums.AccountType;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Payment;
import com.harun.entity.models.Transaction;
import jakarta.persistence.Column;
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
public class AccountDTO {
    Long id;

    String accountNumber;

    AccountType accountType;

    BigDecimal balance;

    BankUser bankUser;

    List<Transaction> transactions = new ArrayList<>();

    List<Payment> payments = new ArrayList<>();
}
