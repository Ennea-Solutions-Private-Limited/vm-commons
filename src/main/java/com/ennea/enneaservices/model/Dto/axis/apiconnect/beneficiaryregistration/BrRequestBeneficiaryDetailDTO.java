package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrRequestBeneficiaryDetailDTO {

    @JsonProperty("apiVersion")
    private String version;

    @JsonProperty("beneLEI")
    private String beneLEI;

    @JsonProperty("beneCode")
    private String beneficiaryCode;

    @JsonProperty("beneName")
    private String beneficiaryName;

    @JsonProperty("beneAccNum")
    private String beneficiaryAccountNumber;

    @JsonProperty("beneIfscCode")
    private String ifscCode;

    @JsonProperty("beneAcType")
    private String accountType;

    @JsonProperty("beneBankName")
    private String bankName;

    @JsonProperty("beneAddr1")
    private String addressLine1;

    @JsonProperty("beneAddr2")
    private String addressLine2;

    @JsonProperty("beneAddr3")
    private String addressLine3;

    @JsonProperty("beneCity")
    private String city;

    @JsonProperty("beneState")
    private String state;

    @JsonProperty("benePincode")
    private String pincode;

    @JsonProperty("beneEmailAddr1")
    private String email;

    @JsonProperty("beneMobileNo")
    private String mobileNumber;

}
