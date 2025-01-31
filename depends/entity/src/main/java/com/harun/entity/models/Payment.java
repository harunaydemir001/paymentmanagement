package com.harun.entity.models;


import com.harun.entity.base.BaseEntity;
import com.harun.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payments")
public class Payment extends BaseEntity<Long> {

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
