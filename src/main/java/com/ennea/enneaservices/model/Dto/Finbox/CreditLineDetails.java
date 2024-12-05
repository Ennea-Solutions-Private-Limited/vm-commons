package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

@Data
public class CreditLineDetails {


    private String status;
    private Long maxLimit;
    private Long availableLimit;
    private String validity;
    private String createdAt;
    private String inactiveReason;
}
