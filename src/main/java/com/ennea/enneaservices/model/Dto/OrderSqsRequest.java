package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class OrderSqsRequest {
    private Long orderId;

    private Long userId;

    private String ingestionType;
}
