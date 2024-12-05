package com.ennea.enneaservices.model.purchaseorderV2;

import com.ennea.enneaservices.model.Dto.InventoryAvailabilityDTO;
import com.ennea.enneaservices.model.Dto.UserMetadataDTO;
import lombok.Data;

@Data
public class PurchaseOrderDetailDTO {

    private Long id;

    private String productName;

    private InventoryAvailabilityDTO inventoryAvailability;

    private InventoryAvailabilityDTO suggestedProduct;

    private String supplierName;

    private UserMetadataDTO supplier;

    private int quantity;
}
