package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.util.List;

@Data
public class RetailBillDTO {

    private Long customerId;

    private String phoneNumber;

    private String customerName;

    private String referenceId;

    private List<RetailBillProductDTO> products;

    private int discount;

    private double value;
}
