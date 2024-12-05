package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.List;

@Data
public class CSquareOrderConfirmedItems {

    private List<CSquareOrderResponseItemDTO> orderedSku;
}
