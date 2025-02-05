package com.harun.common.dto;

import com.harun.common.enums.PaymentSagaStep;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Component
public class PaymentSagaState {
    Long sourceAccountId;
    Long targetAccountId;
    BigDecimal amount;
    PaymentSagaStep currentStep;
    String failureReason;
    Long transactionId;
}