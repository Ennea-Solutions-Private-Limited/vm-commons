package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.Data;

import java.util.List;

@Data
public class FareEstimateResponse {
    private List<VehicleDetails> vehicles;
}

