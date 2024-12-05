package com.ennea.enneaservices.model.porter.trackOrder;

import lombok.Data;

@Data
public class FareDetails {
    // Estimated fare details (nullable)
    private EstimatedFareDetails estimatedFareDetails;

    // Actual fare details (nullable)
    private ActualFareDetails actualFareDetails;
}
