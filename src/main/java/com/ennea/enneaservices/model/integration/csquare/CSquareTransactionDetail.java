package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareTransactionDetail {
    @JsonProperty("itemCode")
    private String productCode;

    private String itemUcode;

    @JsonProperty("itemName")
    private String productName;
    @JsonProperty("packName")
    private String packing;

    @JsonProperty("batchNo")
    private String batch;

    @JsonProperty("expDate")
    private String expiryDate;
    @JsonProperty("qty")
    private String quantity;
    @JsonProperty("schemeQty")
    private String free;

    private String schemeDiscount;
    private String mrp;
    private String rate;
    private String vatPercent;
    private String cstPercent;
    private String vatSaleFlag;
    private String taxOnSchemeFlag;

    private String discountPercent;

    private String cgstPercent;
    private String cgstAmount;
    private String sgstPercent;
    private String sgstAmount;
    private String igstPercent;
    private String igstAmount;
    private String cessPercent;
    private String cessAmount;
    private String looseQty;
    private String hsnCode;
    private String taxOnSch;
    @JsonProperty("mfacName")
    private String manufacturer;
}
