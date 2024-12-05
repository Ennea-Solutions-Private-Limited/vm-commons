package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserProfileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty
    private String businessName;

    private String displayName;

    private String contactPerson;

    @NotEmpty
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private String emailId;

    private Long phoneNumber;

    private Long contactNumber;

    private AddressDTO address;

    private String profileImage;

    @NotEmpty
    private String drugLicense;

    private RoleDTO role;

    private Long uuid;

    private UserStatusDTO userStatus;

    private String gstin;

    @NotNull
    private LocalDate drugLicenseExpiry;

    private String code;

    private String blockComment;

    private String additionalInfo;

    private boolean verified;

    private String drugLicenseUrl;

    private Long whatsappNumber;

    private Boolean whatsappCheckbox = Boolean.TRUE;

    private LocationDto location;

    private String profileImageUrl;
}
