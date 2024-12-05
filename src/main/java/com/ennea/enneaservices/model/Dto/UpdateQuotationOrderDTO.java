package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateQuotationOrderDTO implements Serializable {
    private long quotationId;
    private long customerId;
    private String status;
}
