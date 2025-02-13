package com.harun.paymentprocessingservice.strategy.impl;

import com.harun.entity.models.Card;
import com.harun.paymentprocessingservice.saga.PaymentSagaOrchestrator;
import com.harun.paymentprocessingservice.service.CardService;
import com.harun.paymentprocessingservice.strategy.PayStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("cardStrategy")
@RequiredArgsConstructor
public class CardStrategy implements PayStrategy {

    private final CardService cardService;
    private final PaymentSagaOrchestrator paymentSagaOrchestrator;

    @Override
    public String pay(BigDecimal amount, Card sourceCard, Long targetAccountId) {
        paymentSagaOrchestrator.processPayment(sourceCard.getAccount().getId(), targetAccountId, amount);
        return "Pay with card is successful";
    }

    @Override
    public String pay(BigDecimal amount) {
        return null;
    }
}
