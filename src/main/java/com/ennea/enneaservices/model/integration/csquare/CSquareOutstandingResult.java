package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareOutstandingResult {

    private Long totalOutstandingBills;

    private List<CSquareOutstanding> currentOutstanding = new ArrayList<>();
}
