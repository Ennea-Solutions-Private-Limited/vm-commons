package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareInventorySchemeResponse {
    private String type;
    private String responseCode;
    private String success;
    private CSquareInventorySchemeResponseResult result;
}
