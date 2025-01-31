package com.harun.paymentprocessingservice.dto;

import com.harun.entity.enums.PaymentStatus;
import com.harun.entity.models.Account;
import com.harun.entity.models.BankUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    Long id;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;

    Account account;

    BankUser bankUser;
}
