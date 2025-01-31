package com.harun.common.models;

import com.harun.common.base.BaseDate;
import com.harun.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payments")
public class Payment extends BaseDate<Long> {

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private BankUser bankUser;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
