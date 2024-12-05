package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CSquarePaymentReceiptResult {
    ArrayList<CSquarePaymentReceiptResultItem> receiptsNotProcessed = new ArrayList<>();
}
