package com.harun.entity.models;


import com.harun.entity.base.BaseEntity;
import com.harun.entity.embeddables.AmountEmbed;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "payments")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity<Long> implements Serializable {

    @Embedded
    AmountEmbed amount;

    @Column(name = "account_id", nullable = false)
    Long accountId;

    @Column(name = "transaction_id")
    Long transactionId;

    @SequenceGenerator(name = "payments_seq", sequenceName = "payments_seq", allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
