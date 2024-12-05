package com.ennea.enneaservices.model.purchaseorderV2;


import com.ennea.enneaservices.model.InventoryAvailability;
import com.ennea.enneaservices.model.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PurchaseOrderDetails {

    private Long id;

    private String productName;

    @Transient
    private transient InventoryAvailability inventoryAvailability;

    @ManyToOne
    private InventoryAvailability suggestedProduct;

    private String supplierName;

    private String productCode;

    @ManyToOne
    private User supplier;

    private int quantity;

}
