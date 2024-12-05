package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareItemMasterDetail {
    private String itemCode;
    private String itemName;
    private String pack;
    private String manufacturer;
    private String minSaleQty;
    private String maxSaleQty;
    private String lastModifiedDate;
    private String activeFlag;
    private String packName;
    private String schCloseDate;
    private CSquareSchemeDetails schemeDetails;
}
