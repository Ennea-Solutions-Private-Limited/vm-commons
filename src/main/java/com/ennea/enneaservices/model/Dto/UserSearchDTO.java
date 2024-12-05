package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String businessName;

    private String contactPerson;

    private Long phoneNumber;

    private AddressDTO address;

    private String drugLicense;

    private String drugLicenseKey;

    private RoleDTO role;

    private Long uuid;

}
