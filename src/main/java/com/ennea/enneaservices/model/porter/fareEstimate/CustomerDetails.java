package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.Data;

@Data
public class CustomerDetails {
    // REQUIRED - customer name
    private String name;

    // REQUIRED - country_code ,phone number of customer
    private FareContactDetails mobile;
}
