package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.Data;

@Data
public class FareContactDetails {
    // REQUIRED - customer provides the country code as a string, for example, “+91”
    private String countryCode;

    // REQUIRED - phone number should be without the country code
    private String number;
}
