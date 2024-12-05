package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Scheme;
import lombok.Data;

@Data
public class QuantityMetadataDTO {

    private float orderedQuantity;

    private float freeQuantity;

    private float finalItemQuantity;

    private double rate;

    private Scheme scheme = new Scheme();

    private double discount;
}
