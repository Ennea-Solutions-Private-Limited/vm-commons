package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Settings;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String businessName;

    private String contactPerson;

    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private String emailId;

    private Long phoneNumber;

    private AddressDTO address;

    private int pinCode;

    private String drugLicense;

    private String drugLicenseKey;

    private RoleDTO role;

    private Settings settings;

    private Long uuid;

    private String promoCode;

    private String gstin;

    private String code;

    private String additionalInfo;
}
