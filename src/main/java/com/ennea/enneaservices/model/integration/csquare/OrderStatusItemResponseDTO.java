package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class OrderStatusItemResponseDTO {

    private String orderCheck;
    private String c2RefNo;
    private String provRefno;
    private String orderStatus;
}
