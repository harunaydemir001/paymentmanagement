package com.harun.invoiceservice.service.impl;

import com.harun.common.dto.InvoiceDTO;
import com.harun.common.enums.ErrorMessage;
import com.harun.entity.enums.InvoiceType;
import com.harun.entity.models.Invoice;
import com.harun.invoiceservice.mapper.MapperGenerator;
import com.harun.invoiceservice.mapper.MapperGeneratorSingleton;
import com.harun.invoiceservice.repository.InvoiceRepository;
import com.harun.invoiceservice.service.InvoiceService;
import com.harun.invoiceservice.strategy.PayStrategy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;
    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    private static String message = "";

    private final InvoiceRepository invoiceRepository;
    private final Map<String, PayStrategy> strategies;

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findByIdOrThrowError(id);
        return mapper.invoiceToInvoiceDTO(invoice);
    }

    @Override
    public InvoiceDTO saveInvoice(Invoice invoice) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return mapper.invoiceToInvoiceDTO(savedInvoice);
    }

    @Override
    public InvoiceDTO updateInvoice(Invoice invoice) {
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return mapper.invoiceToInvoiceDTO(updatedInvoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.findByIdOrThrowError(id);
        message = ErrorMessage.DELETION_SUCCESS.getMessage("Invoice", id);
        logger.info(message);
        invoiceRepository.deleteById(id);
    }

    @Override
    public String payInvoice(InvoiceType invoiceType, Double amount) {
        PayStrategy payStrategy = strategies.get(invoiceType.getDescription());
        return payStrategy.pay(amount);
    }
}
