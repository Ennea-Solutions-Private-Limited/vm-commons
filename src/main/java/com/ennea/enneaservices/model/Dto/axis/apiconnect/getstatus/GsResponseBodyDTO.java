package com.ennea.enneaservices.model.Dto.axis.apiconnect.getstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GsResponseBodyDTO {

    @JsonProperty("data")
    private GsResponseDataDTO gsResponseData;

    private String message;

    private String status;
}
