package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryenquiry;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "BeneficiaryEnquiryResponse")
public class BeResponseDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("BeneficiaryEnquiryResponseBodyEncrypted")
    private String beneficiaryEnquiryResponseBodyEncrypted;
}
