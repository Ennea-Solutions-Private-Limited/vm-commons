package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareOrderDTO {

    @JsonProperty("provRefNo")
    private String orderId;

    @JsonProperty("orderDetails")
    private List<CSquareOrderDetailDTO> orderDetails = new ArrayList<>();
}
