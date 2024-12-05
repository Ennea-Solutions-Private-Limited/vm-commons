package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquarePaymentReceiptResponse {
    private String type;
    private int responseCode;
    private String success;
    private List<CSquarePaymentReceiptResultItem> result = new ArrayList<>();
}
