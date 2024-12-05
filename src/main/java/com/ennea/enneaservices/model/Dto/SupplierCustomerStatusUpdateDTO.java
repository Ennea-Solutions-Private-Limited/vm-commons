package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SupplierCustomerStatusUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private int statusId;

    private String customerPartyName;

    private String customerPartyCode;

    private Double minOrderValue;

    private Boolean hideScheme;

    private Boolean showStock;

}
