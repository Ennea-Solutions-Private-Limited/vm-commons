package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartyOrderMetricsDTO {

    private UserMetadataDTO customer;

    private double orderValue;
}
