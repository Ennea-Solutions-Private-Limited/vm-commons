package com.ennea.enneaservices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CartInventory {

    @ManyToOne
    private InventoryAvailability inventoryAvailability;

    private int orderedQuantity;

    private double orderedValue;

    private double discount;

    @Column(name = "s3_reference")
    private String s3Reference;

    private transient String status;

    private boolean hold = false;
}
