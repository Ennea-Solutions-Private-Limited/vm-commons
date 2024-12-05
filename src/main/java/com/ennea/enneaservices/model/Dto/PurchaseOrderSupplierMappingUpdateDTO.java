package com.ennea.enneaservices.model.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseOrderSupplierMappingUpdateDTO {
    @NonNull
    private Long id;

    @NonNull
    private Long supplierId;
}
