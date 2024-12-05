package com.ennea.enneaservices.model.Dto.axis.apiconnect.getaccountstatement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GasRequestBodyDTO {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("corpAccNum")
    private String corporateAccountNumber;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("stmtFromDate")
    private String fromDate;

    @JsonProperty("stmtToDate")
    private String toDate;

    @JsonProperty("emailId")
    private String email;

    @JsonProperty("format")
    private String format;

    private String checksum;
}
