package com.ennea.enneaservices.model.porter.trackOrder;

import com.ennea.enneaservices.enums.porter.Status;
import lombok.Data;

@Data
public class OrderResponse {
    // Unique order id
    private String orderId;

    // Order status (One of [open, accepted, live, completed, ended, cancelled])
    private Status status;

    // Information related to the partner
    private PartnerInfo partnerInfo;

    // Timings related to the order (in epoch seconds)
    private OrderTimings orderTimings;

    // Fare details for the order
    private FareDetails fareDetails;
}

