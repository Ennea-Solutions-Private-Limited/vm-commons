package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DistrictDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    @NotEmpty
    private String name;
    @NotNull
    private StateDTO state;
}
