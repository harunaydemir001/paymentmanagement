package com.harun.entity.models;


import com.harun.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @NotNull
    @DecimalMin("1.00")
    BigDecimal amount;

    @Column(name = "account_id", nullable = false)
    Long accountId;

    @Column(name = "transaction_id", unique = true)
    Long transactionId;
}
