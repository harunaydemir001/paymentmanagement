package com.harun.invoiceservice.mapper;

import com.harun.common.dto.InvoiceDTO;
import com.harun.entity.models.Invoice;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    InvoiceDTO invoiceToInvoiceDTO(Invoice invoice);
}