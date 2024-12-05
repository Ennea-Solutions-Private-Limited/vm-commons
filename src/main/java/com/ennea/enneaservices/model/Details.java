package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class Details {

    private String productName;

    private String packing;

    private String productCode;

    private String alias;

    private float orderedQuantity;

    private float originalQuantity;

    private float freeQuantity;

    private float billedOrderedQuantity;

    private float billedFreeQuantity;

    private double value;

    private double rate;

    private double mrp;

    private LocalDate expiryDate;

    private String division;

    private int deal;

    private int free;

    private double discount;

    private transient CartInventory cartInventory;

    private transient InventoryAvailability inventoryAvailability;
}
