package com.ennea.enneaservices.model.Dto;


import lombok.Data;

@Data
public class UpdatePreCartItemDTO {

    private Long id;

    private String productName;

    private int quantity;

}
