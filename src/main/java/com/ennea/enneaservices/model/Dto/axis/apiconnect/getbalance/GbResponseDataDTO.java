package com.ennea.enneaservices.model.Dto.axis.apiconnect.getbalance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GbResponseDataDTO {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("corpAccNum")
    private String corporateAccountNumber;

    @JsonProperty("Balance")
    private String balance;

    private String checksum;
}
