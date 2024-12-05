package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SupplierCustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserProfileDTO supplier;

    private UserProfileDTO customer;

    private RequestStatusDTO requestStatus;

    private String customerPartyName;

    private double outstandingAmount;

    private double minimumDue;

    private double creditLimit;

    private int duration;

    private String customerPartyCode;

    private Double minOrderValue;

    private Boolean hideScheme;

    private Boolean showStock;

    private long priority;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;
}
