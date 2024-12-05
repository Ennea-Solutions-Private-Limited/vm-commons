package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

@Data
public class CreditLineTransactionsDetailsDTO {
    private boolean status;
    private String error;
    private CreditLineTransactionsDetailsResponse data;
}
