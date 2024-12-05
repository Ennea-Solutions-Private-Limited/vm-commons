package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareOrderItemsNotProcessed {

    private String provRefNo;

    private CSquareItemsNotProcessed itemDetails;

}
