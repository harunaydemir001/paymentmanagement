package com.harun.entity.models;

import com.harun.entity.base.BaseEntity;
import com.harun.entity.enums.InvoiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice extends BaseEntity<Long> implements Serializable {

    @Enumerated(EnumType.STRING)
    InvoiceType invoiceType;

    @NotNull
    @DecimalMin("0.00")
    BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_user_id", nullable = false)
    BankUser bankUser;

    @Column(name = "is_paid")
    boolean isPaid = false;

    @SequenceGenerator(name = "invoices_seq", sequenceName = "invoices_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoices_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}