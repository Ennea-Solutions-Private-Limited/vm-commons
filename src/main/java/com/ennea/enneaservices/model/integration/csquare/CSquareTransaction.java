package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CSquareTransaction {

    private String provRefNo;

    private List<List<CSquareTransactionHeaderDetail>> invoiceHeader = new ArrayList<>();

    private Map<String, List<CSquareTransactionDetail>> invoiceDetails = new HashMap<>();

}
