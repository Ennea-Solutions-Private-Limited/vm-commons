package com.ennea.enneaservices.model.Dto.axis.apiconnect.getbalance;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "GetAccountBalanceResponse")
public class GbResponseDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("GetAccountBalanceResponseBodyEncrypted")
    private String getAccountBalanceResponseBodyEncrypted;
}
