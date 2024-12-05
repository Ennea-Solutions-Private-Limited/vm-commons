package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryenquiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BeResponseBeneficiaryDetailDTO {

    @JsonProperty("benePincode")
    private String pincode;

    @JsonProperty("beneCode")
    private String beneficiaryCode;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("beneAcType")
    private String accountType;

    @JsonProperty("beneMobileNo")
    private String mobileNumber;

    @JsonProperty("beneBankName")
    private String bankName;

    @JsonProperty("beneName")
    private String name;

    @JsonProperty("authorizedBy")
    private String authorizedBy;

    @JsonProperty("beneIfscCode")
    private String ifscCode;

    @JsonProperty("authorizedDate")
    private String authorizedDate;

    @JsonProperty("beneEmailAddr1")
    private String email;

    @JsonProperty("beneAccNum")
    private String accountNumber;

    @JsonProperty("status")
    private String status;
}
