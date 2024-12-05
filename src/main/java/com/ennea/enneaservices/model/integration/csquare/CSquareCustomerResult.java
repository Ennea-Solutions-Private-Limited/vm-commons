package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.List;

@Data
public class CSquareCustomerResult {
    private long customerCount;
    private List<CSquareCustomerDetail> customerDetails;
}
