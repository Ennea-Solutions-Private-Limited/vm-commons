package com.ennea.enneaservices.enums.porter;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PorterOrderStatusEnum {

    PAYMENT_BEGIN("Awaiting Payment", null, true, true),
    PAYMENT_FAIL("Not Paid", null, true, true),
    PAYMENT_SUCCESS("Payment Complete", null, true, true),
    PAYMENT_EXPIRED("Payment Expired", null, true, true),
    CREATED("Order Created", null, false, false),
    ACCEPTED("Order Accepted", "order_accepted", true, false),
    STARTED("Trip Started", "order_start_trip", true, false),
    ENDED("Trip Completed", "order_end_job", true, true),
    REOPENED("Order Re-Opened", "order_reopen", true, false),
    CANCELLED("Order Cancelled", "order_cancel", true, true);

    private final String text;
    private final String webhookStatus;
    private final boolean disableCancel;
    private final boolean disableTracking;

    PorterOrderStatusEnum(String text, String webhookStatus, boolean disableCancel, boolean disableTracking) {
        this.text = text;
        this.webhookStatus = webhookStatus;
        this.disableCancel = disableCancel;
        this.disableTracking = disableTracking;
    }

    public static PorterOrderStatusEnum getByWebhookStatus(String webhookStatus) {
        return Arrays.stream(values())
            .filter(e -> e.webhookStatus != null && e.webhookStatus.equals(webhookStatus))
            .findFirst()
            .orElse(null);
    }

    public static PorterOrderStatusEnum getByText(String text) {
        return Arrays.stream(values())
            .filter(e -> e.text != null && e.text.equals(text))
            .findFirst()
            .orElse(null);
    }

}
