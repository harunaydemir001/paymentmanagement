package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.embeddables.AmountEmbed;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO extends BaseDTO<Long> {

    AmountEmbed amount;

    Long accountId;

    Long transactionId;
}
