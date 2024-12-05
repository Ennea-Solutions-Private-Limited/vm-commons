package com.ennea.enneaservices.model.Dto.axis.apiconnect.getstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GsResponseTransactionDetailDTO {

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("statusDescription")
    private String description;

    @JsonProperty("batchNo")
    private String batchNumber;

    @JsonProperty("utrNo")
    private String settlementReferenceNumber;

    @JsonProperty("processingDate")
    private String processingDate;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("crn")
    private String customerReferenceNumber;

    @JsonProperty("transactionStatus")
    private String status;
}
