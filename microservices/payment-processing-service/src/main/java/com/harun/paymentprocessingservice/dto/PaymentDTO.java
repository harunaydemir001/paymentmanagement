package com.harun.paymentprocessingservice.dto;

import com.harun.entity.enums.PaymentStatus;
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
public class PaymentDTO {

    Long id;

    BigDecimal amount;

    PaymentStatus paymentStatus;

    Account account;

    BankUser bankUser;
}
