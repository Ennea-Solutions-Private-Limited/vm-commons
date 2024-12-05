package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ReferUserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String businessName;

    private Long phoneNumber;

    private String city;

}
