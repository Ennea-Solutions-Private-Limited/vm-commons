package com.ennea.enneaservices.model.Dto.axis.apiconnect.getbalance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GbResponseBodyDTO {

    @JsonProperty("data")
    private GbResponseDataDTO gbResponseData;

    private String message;

    private String status;
}
