package com.harun.common.dto;

import com.harun.entity.models.BankUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignDTO {

    String name;

    BigDecimal discountPercentage;

    Set<BankUser> bankUsers = new HashSet<>();

    LocalDate startDate;

    LocalDate endDate;

}
