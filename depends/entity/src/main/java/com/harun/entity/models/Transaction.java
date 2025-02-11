package com.harun.entity.models;

import com.harun.entity.base.BaseEntity;
import com.harun.entity.embeddables.AmountEmbed;
import com.harun.entity.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction extends BaseEntity<Long> implements Serializable {

    @Embedded
    AmountEmbed amount;

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

    @SequenceGenerator(name = "transactions_seq", sequenceName = "transactions_seq", allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
