package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquarePaymentReceiptItemDTO {

    private String provRefNo;
    private String remark;

    private String custCode;

    private String chequeNo;

    private String ChequeDate;

    private Double chequeTotal;
}
