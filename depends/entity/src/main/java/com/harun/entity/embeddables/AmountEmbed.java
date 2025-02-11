package com.harun.entity.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AmountEmbed {

    @DecimalMin("1.00")
    @Column(name = "amount", precision = 19, scale = 2, nullable = false)
    @Positive
    BigDecimal amount;
}
