package com.harun.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    PENDING("Pending", "Payment is awaiting processing"),
    COMPLETED("Completed", "Payment has been successfully processed"),
    FAILED("Failed", "Payment could not be processed"),
    CANCELLED("Cancelled", "Payment was cancelled by the user"),
    REFUNDED("Refunded", "Payment was refunded to the user");

    private final String displayName;
    private final String description;
}
