package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class NewInventoryDTO implements Serializable {

    private String productName;

    private int availability;

    private String division;

    private Double mrp;

    private Double ptr;

    private LocalDate expiryDate;

    private Double taxRate;
}
