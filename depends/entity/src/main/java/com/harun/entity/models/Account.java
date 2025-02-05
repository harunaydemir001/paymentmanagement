package com.harun.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.harun.entity.base.BaseEntity;
import com.harun.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity<Long> implements Serializable {

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Column(name = "balance", precision = 19, scale = 2)
    BigDecimal balance;

    @Column(unique = true, nullable = false)
    String iban;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @Fetch(FetchMode.JOIN)
    BankUser bankUser;

    @ElementCollection
    @CollectionTable(name = "account_transactions", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "transaction_id")
    @Fetch(FetchMode.JOIN)
    List<Long> transactionIds = new ArrayList<>();

    @SequenceGenerator(name = "accounts_seq", sequenceName = "accounts_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
