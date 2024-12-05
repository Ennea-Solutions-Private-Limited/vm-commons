package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrResponseBeneficiaryDetailDTO {

    @JsonProperty("beneMobileNo")
    private String mobileNumber;

    @JsonProperty("statusDesc")
    private String statusDescription;

    @JsonProperty("beneBankName")
    private String bankName;

    @JsonProperty("beneName")
    private String name;

    @JsonProperty("beneIfscCode")
    private String ifscCode;

    @JsonProperty("beneCode")
    private String beneficiaryCode;

    @JsonProperty("beneEmailAddr1")
    private String email;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("status")
    private String status;

    @JsonProperty("beneAccNum")
    private String accountNumber;
}
