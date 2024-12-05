package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.List;

@Data
public class CSquareTransactionDetails {

    List<List<CSquareTransactionDetail>> invoiceDetails;
}
