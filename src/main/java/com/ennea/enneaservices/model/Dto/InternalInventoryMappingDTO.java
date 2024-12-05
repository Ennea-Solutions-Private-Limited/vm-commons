package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class InternalInventoryMappingDTO {

    private Long id;

    private String productName;

    private String packing;

    private int availability;

    private double mrp;

    private UserMetadataDTO supplier;

    private String genkey;
}
