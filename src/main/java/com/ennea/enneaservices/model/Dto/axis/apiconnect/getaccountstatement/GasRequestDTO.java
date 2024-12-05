package com.ennea.enneaservices.model.Dto.axis.apiconnect.getaccountstatement;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "GetAccountStatementRequest")
public class GasRequestDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("GetAccountStatementRequestBodyEncrypted")
    private String getAccountStatementRequestBodyEncrypted;
}
