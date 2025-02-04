package com.harun.invoiceservice.service;

import com.harun.common.dto.InvoiceDTO;
import com.harun.entity.enums.InvoiceType;
import com.harun.entity.models.Invoice;

public interface InvoiceService {
    InvoiceDTO getInvoiceById(Long id);

    InvoiceDTO saveInvoice(Invoice invoice);

    InvoiceDTO updateInvoice(Invoice invoice);

    void deleteInvoice(Long id);

    void payInvoice(InvoiceType invoiceType, Double amount);
}
