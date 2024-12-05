package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareOrderConfirmedDetails {

    private String provRefNo;
    private String c2RefNo;
    private CSquareOrderConfirmedItems itemDetails;
}
