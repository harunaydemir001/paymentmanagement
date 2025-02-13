package com.harun.paymentprocessingservice.model;

import com.harun.entity.models.Card;
import com.harun.paymentprocessingservice.enums.PayType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayRequest {
    @NotNull
    PayType payType;
    @NotNull
    BigDecimal amount;
    Card sourceCard;
    Long targetAccountId;
}
