package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

@Data
public class CreditLineTransactionResponseDTO {

    private String error;

    private boolean status;

    private CreditLineTransactionResponseMessage data;

}
