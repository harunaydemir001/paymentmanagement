package com.harun.invoiceservice.mapper;

import com.harun.common.dto.InvoiceDTO;
import com.harun.entity.models.Invoice;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T00:59:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public InvoiceDTO invoiceToInvoiceDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setInvoiceType( invoice.getInvoiceType() );
        invoiceDTO.setAmount( invoice.getAmount() );
        invoiceDTO.setBankUser( invoice.getBankUser() );
        invoiceDTO.setPaid( invoice.isPaid() );

        return invoiceDTO;
    }
}
