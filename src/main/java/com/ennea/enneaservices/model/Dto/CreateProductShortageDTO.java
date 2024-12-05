package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductShortageDTO {

    @NotBlank
    private String productName;

    private int quantity;
}
