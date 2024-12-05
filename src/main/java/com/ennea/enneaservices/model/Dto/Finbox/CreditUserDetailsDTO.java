package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

@Data
public class CreditUserDetailsDTO {

    private boolean status;
    private String error;
    private CreditUserDetailsResponse data;
}
