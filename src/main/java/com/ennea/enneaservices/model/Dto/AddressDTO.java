package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
public class AddressDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String addressLine1;
    private String addressLine2;
    @NotEmpty
    private String city;
    @NotEmpty
    private int pincode;
    private DistrictDTO district;
}
