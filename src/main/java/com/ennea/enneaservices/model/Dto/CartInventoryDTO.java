package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class CartInventoryDTO {

    private InventoryAvailabilityDTO inventoryAvailability;

    private int orderedQuantity;

    private double discount;

    private double orderedValue;

    private long purchaseOrderItemId;

    private String status;

    private String s3Reference;

    private boolean hold;

    // To be removed
    private double specialRate = 0;

    // To be removed
    private float specialFreeQuantity = 0;
}
