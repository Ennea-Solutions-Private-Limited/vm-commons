package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareInventorySchemeResponseResult {
    private boolean dataExists;
    private int rowCount;
    private List<CSquareInventorySchemeDetail> details = new ArrayList<>();
}
