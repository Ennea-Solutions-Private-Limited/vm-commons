package com.ennea.enneaservices.model.porter.trackOrder;

import lombok.Data;

@Data
public class Mobile {
    // Country code for the mobile number
    private String countryCode;

    // Mobile number of the partner
    private String mobileNumber;
}
