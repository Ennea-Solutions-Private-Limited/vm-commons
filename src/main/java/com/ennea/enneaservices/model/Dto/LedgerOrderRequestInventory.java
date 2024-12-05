package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class LedgerOrderRequestInventory {

    private long id;
    private int quantity;
    private int free;
    private int discount;
}
