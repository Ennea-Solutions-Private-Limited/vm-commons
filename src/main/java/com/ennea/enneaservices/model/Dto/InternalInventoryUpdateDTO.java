package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.util.List;

@Data
public class InternalInventoryUpdateDTO {

    private String key;
    private String name;
    private String packing;
    private List<String> added;
    private List<String> removed;
}
