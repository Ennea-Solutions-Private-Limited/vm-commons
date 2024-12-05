package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class InvoiceDetail {

    private String hsn;

    private String productCode;

    private String productName;

    private String packing;

    private String manufacturer;

    private String division;

    private String batchNumber;

    private LocalDate manufacturingDate;

    private LocalDate expiryDate;

    private Float quantity;

    private Float free;

    private Double productRate;

    private Double actualRate;

    private Double mrp;

    private Double value;

    private Double discount;

    private Double taxableAmount;

    private Double tax;

    private Double volumeDiscount;

}