package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.List;

@Data
public class CSquareTransactionHeaderDetails {
    List<List<CSquareTransactionHeaderDetail>> invoiceHeader;
}
