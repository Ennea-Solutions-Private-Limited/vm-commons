package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DisbursalStatusDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;
}
