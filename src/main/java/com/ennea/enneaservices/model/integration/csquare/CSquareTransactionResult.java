package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareTransactionResult {

    @JsonProperty("InvoiceDetailsFound")
    List<CSquareTransaction> invoiceDetailsFound = new ArrayList<>();
}
