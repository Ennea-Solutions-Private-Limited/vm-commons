package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryenquiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BeResponseDataDTO {

    @JsonProperty("count")
    private int count;

    private String checksum;

    @JsonProperty("beneDetails")
    private List<BeResponseBeneficiaryDetailDTO> beResponseBeneficiaryDetails = new ArrayList<>();
}
