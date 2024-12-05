package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductShortageDTO implements Serializable {

    private long id;

    private String productName;

    private int quantity;
}
