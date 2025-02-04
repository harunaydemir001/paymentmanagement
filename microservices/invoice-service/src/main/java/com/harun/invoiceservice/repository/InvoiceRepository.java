package com.harun.invoiceservice.repository;

import com.harun.dalcommon.repository.base.JPABaseRepository;
import com.harun.entity.models.Invoice;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JPABaseRepository<Invoice, Long> {
}
