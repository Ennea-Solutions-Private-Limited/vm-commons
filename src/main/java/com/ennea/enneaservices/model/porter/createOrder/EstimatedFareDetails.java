package com.ennea.enneaservices.model.porter.createOrder;

import lombok.Data;

@Data
public class EstimatedFareDetails {
    // Currency for the fare details
    private String currency;

    // Minor amount of the currency (e.g., paise for INR)
    private int minorAmount;
}
