package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrRequestBodyDTO {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("beneinsert")
    private List<BrRequestBeneficiaryDetailDTO> brRequestBeneficiaryDetails = new ArrayList<>();

    private String checksum;
}
