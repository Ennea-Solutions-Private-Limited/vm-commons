package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareItemMasterResponse {
    private String type;
    private String responseCode;
    private String success;
    private CSquareItemMasterResult result;
}
