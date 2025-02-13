package com.harun.paymentprocessingservice.strategy;

import com.harun.entity.models.Card;

import java.math.BigDecimal;

public interface PayStrategy {

    String pay(BigDecimal amount);

    String pay(BigDecimal amount, Card sourceCard, Long targetAccountId);
}