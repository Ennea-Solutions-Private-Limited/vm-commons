package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.Data;

@Data
public class AddressLatLng {
    // REQUIRED - latitude for pickup/drop location, should be given up to at least 4 decimal places
    private Double lat;

    // REQUIRED - longitude for pickup/drop location, should be given up to at least 4 decimal places
    private Double lng;
}
