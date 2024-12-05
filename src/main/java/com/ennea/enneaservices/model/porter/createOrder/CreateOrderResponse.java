package com.ennea.enneaservices.model.porter.createOrder;

import lombok.Data;

@Data
public class CreateOrderResponse {
    // Client request id used for tracking the request (UUID)
    private String requestId;

    // Unique order id
    private String orderId;

    // Estimated pickup time in Epoch timestamp (milliseconds)
    // Null when supply is not available
    private Long estimatedPickupTime;

    // Fare details for the order
    private EstimatedFareDetails estimatedFareDetails;

    // URL for tracking the order
    private String trackingUrl;
}
