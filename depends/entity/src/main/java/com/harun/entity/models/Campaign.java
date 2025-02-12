package com.harun.entity.models;

import com.harun.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Campaign extends BaseEntity<Long> implements Serializable {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "discount_percentage")
    BigDecimal discountPercentage;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "campaign_bank_users",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_user_id")
    )
    @Fetch(FetchMode.JOIN)
    Set<BankUser> bankUsers = new HashSet<>();

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @SequenceGenerator(name = "campaigns_seq", sequenceName = "campaigns_seq", allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campaigns_seq")
    @Override
    public Long getId() {
        return super.getId();
    }
}
