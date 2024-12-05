package com.ennea.enneaservices.model.porter.trackOrder;

import com.ennea.enneaservices.enums.porter.VehicleType;
import lombok.Data;

@Data
public class PartnerInfo {
    // Name registered with porter
    private String name;

    // Vehicle registration number
    private String vehicleNumber;

    // Vehicle type like two_wheeler or others
    private VehicleType vehicleType;

    // Mobile number and country code of the partner
    private Mobile mobile;

    // Secondary mobile number and country code of the partner
    private Mobile partnerSecondaryMobile;

    // Current location of the partner (latitude and longitude)
    private Location location;
}
