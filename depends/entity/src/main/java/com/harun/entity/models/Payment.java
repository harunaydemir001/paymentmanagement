package com.harun.entity.models;


import com.harun.entity.base.BaseEntity;
import com.harun.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payments")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity<Long> implements Serializable {

    @Column(nullable = false)
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_user_id", nullable = false)
    BankUser bankUser;
}
