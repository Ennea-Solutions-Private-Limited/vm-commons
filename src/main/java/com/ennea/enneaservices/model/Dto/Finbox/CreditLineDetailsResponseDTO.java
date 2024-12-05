package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

@Data
public class CreditLineDetailsResponseDTO {

    private String error;

    private boolean status;

    private CreditLineDetails data;
}
