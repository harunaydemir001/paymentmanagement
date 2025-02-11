package com.harun.common.dto;


import com.harun.common.base.BaseDTO;
import com.harun.entity.embeddables.AmountEmbed;
import com.harun.entity.enums.TransactionType;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link Transaction}
 */

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class TransactionDTO extends BaseDTO<Long> {

    AmountEmbed amount;

    TransactionType transactionType;

    Account fromAccount;

    Account toAccount;

    BankUser bankUser;
}
