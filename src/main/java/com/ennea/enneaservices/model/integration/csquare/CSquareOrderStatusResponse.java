package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareOrderStatusResponse {

    private String type;
    private String responseCode;
    private String success;
    private List<OrderStatusItemResponseDTO> result = new ArrayList<>();
}
