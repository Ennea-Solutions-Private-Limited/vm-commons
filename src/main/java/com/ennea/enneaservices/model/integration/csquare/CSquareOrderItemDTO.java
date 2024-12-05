package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareOrderItemDTO {

    @JsonProperty("itemCode")
    private String productCode;

    @JsonProperty("itemQty")
    private String quantity;
}
