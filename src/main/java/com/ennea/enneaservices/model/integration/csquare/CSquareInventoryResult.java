package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.List;

@Data
public class CSquareInventoryResult {
    private long itemCount;
    private List<CSquareInventoryItem> itemDetails;
}
