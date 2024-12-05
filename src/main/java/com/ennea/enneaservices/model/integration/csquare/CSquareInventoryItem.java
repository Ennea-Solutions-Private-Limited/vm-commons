package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareInventoryItem {
    private String itemCode;
    @JsonProperty("quantity")
    private String stock;
    private String mrp;
    private String ptr;
    private String lastModifiedDatetime;
    @JsonProperty("c_batch_no")
    private String batch;
    private String pts;
    private String c_br_code;
    private String expDate;
}
