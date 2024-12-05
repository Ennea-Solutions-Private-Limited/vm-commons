package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FareEstimateRequest {
    // REQUIRED - pickup latitude and longitude
    private AddressLatLng pickupDetails;

    // REQUIRED - drop latitude and longitude
    private AddressLatLng dropDetails;

    // REQUIRED - customer name , customer number , country_code
    private CustomerDetails customer;
}
