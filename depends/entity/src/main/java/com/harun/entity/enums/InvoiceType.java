package com.harun.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InvoiceType {
    ELECTRICITY("Electrıcıty", "electricStrategy"),
    WATER("Water", "waterStrategy"),
    GAS("Gas", "gasStrategy"),
    INTERNET("Internet", "internetStrategy");

    private final String displayName;
    private final String description;

}