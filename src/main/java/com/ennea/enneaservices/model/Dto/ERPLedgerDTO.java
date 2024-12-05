package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPLedgerDTO {


    // partyCode
    @NotBlank
    private String id;

    private ERPAddressDTO address;

    private String phoneNumber;

    private String emailId;

    @NotBlank
    private String name;

    private String code;

    private double balance;

    private String bank;

    private String branch;

    private String gstin;

    private String drugLicense;

    private String drugLicenseExpiryDate;

    private boolean deleted;

    private String salesmanCode;

    private String deliveryAgentCode;

    private String collectionAgentCode;
}
