package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareUploadOrderResponseResult {

    private List<CSquareOrderConfirmedDetails> orderConfirmedDetails = new ArrayList<>();

    private List<CSquareOrderNotProcessed> ordersNotProcessed = new ArrayList<>();

    private List<CSquareOrderItemsNotProcessed> itemsNotProcessed;
}
