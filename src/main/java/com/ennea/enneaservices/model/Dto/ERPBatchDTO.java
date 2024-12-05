package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPBatchDTO {

    private String batch;


    // add to get availability
    private int stock;

    private String manufacturer;
    // concat manufacturer and division to division in IA
    private String division;

    //max for mrp
    private double mrp;

    //ptr
    private double saleRate;

    //purchaseRate to be added
    private double purchaseRate;

    // lowest non-zero ration free/deal
    private int deal;

    private int free;

    private String schemeValidity;

    private String schemePercentage;

    //least expiry date
    private String expiryDate;

    //packing of the least expiry date
    private String packing;

    //packing of the least expiry rate
    private String casePacking;

    //activeStatus
    private boolean deleted;

    //Max tax rate
    private double taxRate;

}
