package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class UserCreditDetailsDTO {

    private String creditProvider;

    private Double approvedAmount;

    private Double utilizedAmount;

    private String status;

    private String applicationLink;

}
