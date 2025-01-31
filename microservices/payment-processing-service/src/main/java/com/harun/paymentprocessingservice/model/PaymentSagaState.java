package com.harun.paymentprocessingservice.model;

import com.harun.entity.enums.PaymentStatus;
import com.harun.paymentprocessingservice.enums.PaymentSagaStep;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class PaymentSagaState {
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private PaymentSagaStep currentStep;
    private String failureReason;
}