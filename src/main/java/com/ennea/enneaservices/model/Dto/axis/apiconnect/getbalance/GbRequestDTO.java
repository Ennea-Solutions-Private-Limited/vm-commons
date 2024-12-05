package com.ennea.enneaservices.model.Dto.axis.apiconnect.getbalance;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "GetAccountBalanceRequest")
public class GbRequestDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("GetAccountBalanceRequestBodyEncrypted")
    private String getAccountBalanceRequestBodyEncrypted;
}
