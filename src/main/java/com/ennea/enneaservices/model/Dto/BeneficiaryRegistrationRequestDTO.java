package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BeneficiaryRegistrationRequestDTO {

    private long beneficiaryUserId;

    private String beneficiaryCode;

    @NotBlank
    private String beneficiaryName;

    @NotBlank
    private String beneficiaryAccountNumber;

    @NotBlank
    private String ifscCode;

    private String accountType;

    private String bankName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String pincode;

    private String email;

    private String mobileNumber;

}
