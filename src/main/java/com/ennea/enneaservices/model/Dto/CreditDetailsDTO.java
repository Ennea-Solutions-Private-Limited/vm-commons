package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class CreditDetailsDTO {

    private String status = "INACTIVE";

    private Double availableLimit;

    private Double maxLimit;

    private String inactiveReason;

    private String applicationLink;
}
