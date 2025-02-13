package com.harun.common.dto;

import com.harun.common.base.BaseDTO;
import com.harun.entity.enums.InvoiceType;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Invoice;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * DTO for {@link Invoice}
 */

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class InvoiceDTO extends BaseDTO<Long> {

    InvoiceType invoiceType;

    BigDecimal amount;

    BankUser bankUser;

    boolean isPaid = false;
}
