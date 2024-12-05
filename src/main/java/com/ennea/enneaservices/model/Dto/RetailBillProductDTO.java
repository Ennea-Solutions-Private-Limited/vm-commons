package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class RetailBillProductDTO {

    private Long productId;

    private String productName;

    private int quantity;

    private Double rate;

    private double taxRate;
}
