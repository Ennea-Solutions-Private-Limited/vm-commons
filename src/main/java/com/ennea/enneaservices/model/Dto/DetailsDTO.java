package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class DetailsDTO {

    private String productName;

    private String packing;

    private String productCode;

    private String alias;

    private float orderedQuantity;

    private float originalQuantity;

    private double value;

    private double rate;

    private double mrp;

    private Date expiryDate;

    private float freeQuantity;

    private String division;

    private int deal;

    private int free;

    private String invoiceId;

    private LocalDate invoiceDate;

    private String batch;

    private String reason;

    private double discount;

    private float billedOrderedQuantity;

    private float billedFreeQuantity;
}
