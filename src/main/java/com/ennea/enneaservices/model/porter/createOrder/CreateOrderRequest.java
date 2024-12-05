package com.ennea.enneaservices.model.porter.createOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    // Unique identifier for the order request
    private UUID requestId;

    // Details of the pickup address
    private Address pickupDetails;

    // Details of the drop address
    private Address dropDetails;

    // Additional comments for the order request (nullable), PFE dashboard comments
    private String additionalComments;
}


