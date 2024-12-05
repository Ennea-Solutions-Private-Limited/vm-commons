package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class PurchaseOrderInventoryMappingDTO {

    private Long id;

    private UserMetadataDTO supplier;

    private String productName;

    private String supplierName;

    private String productCode;

    private InventoryAvailabilityDTO inventoryAvailability;
}

