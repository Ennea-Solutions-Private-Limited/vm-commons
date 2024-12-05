package com.ennea.enneaservices.model.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseOrderInventoryMappingCreateDTO {

    private Long detailId;

    @NonNull
    private Long inventoryAvailabilityId;

    @NonNull
    private String productName;
}
