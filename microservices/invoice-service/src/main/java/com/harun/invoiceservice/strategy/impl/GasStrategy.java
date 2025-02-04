package com.harun.invoiceservice.strategy.impl;

import com.harun.common.enums.EventType;
import com.harun.common.utils.EmailUtil;
import com.harun.common.utils.ReportUtil;
import com.harun.common.utils.StringBuilderUtil;
import com.harun.invoiceservice.constant.MessageTemplates;
import com.harun.invoiceservice.strategy.PayStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GasStrategy implements PayStrategy {
    private static final Logger logger = LoggerFactory.getLogger(GasStrategy.class);

    @Override
    public void pay(Double amount) {
        String message = StringBuilderUtil.buildMessage(MessageTemplates.BILL_PAID, "gas", amount, "IGDAS");
        logger.info(message);
        EmailUtil.sendEmail("harunaydemir001@gmail.com", message, EventType.BILL_PAYMENT.name(), null);
        ReportUtil.createReport(null, EventType.BILL_PAYMENT, message, null);
    }
}
