package com.harun.paymentprocessingservice.dto;

import com.harun.common.enums.PaymentStatus;
import com.harun.common.models.Account;
import com.harun.common.models.BankUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    BigDecimal amount;
    PaymentStatus status;
    BankUser bankUser;
    Account account;
}
