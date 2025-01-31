package com.harun.entity.models;

import com.harun.entity.base.BaseEntity;
import com.harun.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction extends BaseEntity<Long> {

    @Column(nullable = false)
    BigDecimal amount;

    @Column(name = "transaction_date")
    LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_account_id", nullable = false)
    Account fromAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_account_id", nullable = false)
    Account toAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_user_id", nullable = false)
    BankUser bankUser;
}
