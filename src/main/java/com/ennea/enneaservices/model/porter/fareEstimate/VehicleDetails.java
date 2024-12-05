package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.Data;

@Data
public class VehicleDetails {
    private String type;
    private Eta eta;
    private Fare fare;
    private Capacity capacity;
    private SizeDetails size;
}
