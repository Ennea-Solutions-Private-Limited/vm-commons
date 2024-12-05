package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryenquiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BeResponseBodyDTO {

    @JsonProperty("data")
    private BeResponseDataDTO beResponseData;

    private String message;

    private String status;
}
