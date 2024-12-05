package com.ennea.enneaservices.model.Dto.axis.apiconnect.getstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GsRequestBodyDTO {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("crn")
    private List<String> customerReferenceNumbers;

    private String checksum;
}
