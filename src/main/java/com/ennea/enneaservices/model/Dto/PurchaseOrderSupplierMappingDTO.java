package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PurchaseOrderSupplierMappingDTO implements Serializable {

    private Long id;
    private String supplierName;
    private UserMetadataDTO supplier;

}
