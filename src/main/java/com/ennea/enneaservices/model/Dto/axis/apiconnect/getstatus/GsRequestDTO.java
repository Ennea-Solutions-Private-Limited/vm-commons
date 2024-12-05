package com.ennea.enneaservices.model.Dto.axis.apiconnect.getstatus;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "GetStatusRequest")
public class GsRequestDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("GetStatusRequestBodyEncrypted")
    private String getStatusRequestBodyEncrypted;
}
