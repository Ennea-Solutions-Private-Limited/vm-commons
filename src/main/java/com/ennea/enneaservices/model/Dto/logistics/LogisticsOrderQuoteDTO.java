package com.ennea.enneaservices.model.Dto.logistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LogisticsOrderQuoteDTO {

    private String provider;

    private String vehicleType;

    private Integer estimatedFare;

    private String paymentBy;

    private boolean mobile;

    @JsonIgnore
    public Double getEstimatedFareInRupees() {
        return ((double) estimatedFare / 100);
    }
}
