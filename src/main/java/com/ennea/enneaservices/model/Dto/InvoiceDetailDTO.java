package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDetailDTO {

    private String productName;

    private String productCode;

    private String packing;

    private String division;

    private String batchNumber;

    private String hsn;

    private LocalDate expiryDate;

    private double productRate;

    private double actualRate;

    private double mrp;

    private float quantity;

    private float free;

    private Double value;

    private Double tax;

    private float discount;

    private float volumeDiscount;

}