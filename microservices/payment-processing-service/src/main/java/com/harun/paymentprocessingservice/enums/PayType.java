package com.harun.paymentprocessingservice.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PayType {

    CARD("Card", "cardStrategy"),
    EFT("Eft", "eftStrategy");

    private final String displayName;
    private final String description;
    }
