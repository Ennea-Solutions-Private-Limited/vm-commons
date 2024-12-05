package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareInventoryResponse {
    private String type;
    private String responseCode;
    private String success;
    private CSquareInventoryResult result;
}
