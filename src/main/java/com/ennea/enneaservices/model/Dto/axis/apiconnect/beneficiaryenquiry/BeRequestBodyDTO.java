package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryenquiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BeRequestBodyDTO {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("fromDate")
    private String fromDate;

    @JsonProperty("toDate")
    private String toDate;

    @JsonProperty("emailId")
    private String emailId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("beneCode")
    private List<String> beneficiaryCodes = new ArrayList<>();

    private String checksum;
}
