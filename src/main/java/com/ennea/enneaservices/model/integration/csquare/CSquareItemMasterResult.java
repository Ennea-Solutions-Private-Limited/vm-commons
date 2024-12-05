package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.List;

@Data
public class CSquareItemMasterResult {
    private long itemCount;
    private List<CSquareItemMasterDetail> itemDetails;
}
