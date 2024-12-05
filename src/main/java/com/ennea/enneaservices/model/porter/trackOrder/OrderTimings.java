package com.ennea.enneaservices.model.porter.trackOrder;

import lombok.Data;

@Data
public class OrderTimings {
    // Pickup time in epoch seconds
    private long pickupTime;

    // Time when the order was accepted (nullable)
    private Long orderAcceptedTime;

    // Time when the order started (nullable)
    private Long orderStartedTime;
}
