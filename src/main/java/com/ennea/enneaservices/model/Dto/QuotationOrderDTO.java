package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class QuotationOrderDTO implements Serializable {

    private Long customerId;

    private int quantity;

    private String status;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;
}
