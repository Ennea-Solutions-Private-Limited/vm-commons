package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PendingDisbursalDTO {

    private long id;

    private double paidAmount;

    private LocalDateTime creationDateTime;
}
