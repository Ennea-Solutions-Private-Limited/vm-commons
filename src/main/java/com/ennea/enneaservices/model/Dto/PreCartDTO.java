package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class PreCartDTO {

    private long id;
    private InventoryAvailabilityDTO inventoryAvailability;
    private String productName;
    private String supplierName;
    private int quantity;
}
