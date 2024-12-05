package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InternalInventoryDTO {

    private Long id = 0L;
    private String genkey = "";
    private String name = "";
    private String packing = "";
    private long itemsCount = 0;
    private List<InternalInventoryMappingDTO> mappings = new ArrayList<>();

}
