package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CartDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private int quantity;

    private double rate;

    private int free;

    private double discount;

    private long purchaseOrderItemId;

    private String s3Reference;
}

