package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartHoldUpdateDTO implements Serializable {

    private Long cartId;

    private Long inventoryId;
}
