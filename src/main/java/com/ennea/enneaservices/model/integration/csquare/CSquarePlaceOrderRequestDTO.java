package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquarePlaceOrderRequestDTO {
    @JsonProperty("ordList")
    List<CSquareOrderDTO> orders = new ArrayList<>();
}
