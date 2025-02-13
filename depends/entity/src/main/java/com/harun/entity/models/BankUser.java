package com.harun.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.harun.entity.base.BaseEntity;
import com.harun.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUser extends BaseEntity<Long> implements Serializable {

    @Column(nullable = false, unique = true)
    @Email
    String email;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Gender gender;

    @Column(name = "is_email_verified")
    boolean isEmailVerified = false;

    @Column(name = "is_phone_verified")
    boolean isPhoneVerified = false;

    @Column(name = "occupation")
    String occupation;

    @Column(name = "monthly_income")
    BigDecimal monthlyIncome;

    @Column(name = "credit_score")
    Integer creditScore;

    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Fetch(FetchMode.JOIN)
    Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    Set<Card> cards = new HashSet<>();

    @SequenceGenerator(name = "bank_users_seq", sequenceName = "bank_users_seq", allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_users_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
