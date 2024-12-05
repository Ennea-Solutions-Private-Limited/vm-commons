package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SupplierRepresentativeStatusUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private int statusId;

    private Boolean isMaster;

}
