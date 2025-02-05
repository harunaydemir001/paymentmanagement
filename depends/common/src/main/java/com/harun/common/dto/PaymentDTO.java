package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO extends BaseDTO<Long> {

    BigDecimal amount;

    Account account;

    BankUser bankUser;
}
