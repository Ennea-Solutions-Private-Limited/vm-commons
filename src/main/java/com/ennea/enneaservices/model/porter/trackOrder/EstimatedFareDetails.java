package com.ennea.enneaservices.model.porter.trackOrder;

import lombok.Data;

@Data
public class EstimatedFareDetails {
    // Currency for the fare details
    private String currency;

    // Minor amount of the currency (in Long)
    private Long minorAmount;
}
