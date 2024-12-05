package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrResponseDataDTO {

    @JsonProperty("beneDetails")
    private List<BrResponseBeneficiaryDetailDTO> brResponseBeneficiaryDetails = new ArrayList<>();

    private String checksum;

}
