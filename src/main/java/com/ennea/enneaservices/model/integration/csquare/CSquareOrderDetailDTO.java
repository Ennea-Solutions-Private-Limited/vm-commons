package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareOrderDetailDTO {

    @JsonProperty("custCode")
    private String partyCode;

    @JsonProperty("itemDetails")
    private List<CSquareOrderItemDTO> orderItems = new ArrayList<>();
}
