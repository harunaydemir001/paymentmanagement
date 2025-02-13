package com.harun.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.harun.entity.annotation.card.ValidCardNumber;
import com.harun.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity<Long> implements Serializable {

    @Column(nullable = false, unique = true, length = 16)
    @ValidCardNumber
    String cardNumber;

    @Column(nullable = false)
    LocalDate expiryDate;

    @Column(nullable = false)
    String cvv;

    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    @JoinColumn(name = "bank_user_id", nullable = false)
    BankUser bankUser;

    @SequenceGenerator(name = "card_seq", sequenceName = "card_seq", allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
