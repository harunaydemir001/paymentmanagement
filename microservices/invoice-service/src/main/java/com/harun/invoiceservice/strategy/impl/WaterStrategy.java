package com.harun.invoiceservice.strategy.impl;

import com.harun.common.enums.EventType;
import com.harun.common.feign.impl.PaymentServiceClientImpl;
import com.harun.common.utils.EmailUtil;
import com.harun.common.utils.ReportUtil;
import com.harun.common.utils.StringBuilderUtil;
import com.harun.invoiceservice.constant.MessageTemplates;
import com.harun.invoiceservice.strategy.PayStrategy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("waterStrategy")
@RequiredArgsConstructor
public class WaterStrategy implements PayStrategy {
    private static final Logger logger = LoggerFactory.getLogger(WaterStrategy.class);
    private final PaymentServiceClientImpl paymentServiceClient;

    @Override
    public String pay(Double amount) {
        paymentServiceClient.payInvoice(amount);
        String message = StringBuilderUtil.buildMessage(MessageTemplates.BILL_PAID, "water", amount, "ISKI");
        logger.info(message);
        EmailUtil.sendEmail("harunaydemir001@gmail.com", message, EventType.BILL_PAYMENT.name(), null);
        ReportUtil.createReport(null, EventType.BILL_PAYMENT, message, null);
        return message;
    }
}
