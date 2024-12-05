package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryregistration;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "BeneficiaryRegistrationResponse")
public class BrResponseDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("BeneficiaryRegistrationResponseBodyEncrypted")
    private String beneficiaryRegistrationResponseBodyEncrypted;
}
