package com.ennea.enneaservices.model.porter.createOrder;

import lombok.Data;

@Data
public class AddressDetails {
    // Specific details for apartment address (nullable)
    private String apartmentAddress;

    // Primary street address
    private String streetAddress1;

    // Secondary street address (nullable)
    private String streetAddress2;

    // Landmark information (nullable)
    private String landmark;

    // City information (nullable)
    private String city;

    // State information (nullable)
    private String state;

    // Postal code information (nullable)
    private String pincode;

    // Country information (nullable)
    private String country;

    // Latitude of the address
    private double lat;

    // Longitude of the address
    private double lng;

    // Contact details for the address
    private OrderContactDetails contactDetails;
}
