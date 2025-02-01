package com.harun.paymentprocessingservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    Long sourceAccountId;
    Long targetAccountId;
    BigDecimal amount;
}
