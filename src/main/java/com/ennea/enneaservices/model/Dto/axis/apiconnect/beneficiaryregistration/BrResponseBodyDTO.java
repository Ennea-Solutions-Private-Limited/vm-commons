package com.ennea.enneaservices.model.Dto.axis.apiconnect.beneficiaryregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrResponseBodyDTO {

    @JsonProperty("data")
    private BrResponseDataDTO brResponseData;

    private String message;

    private String status;
}
