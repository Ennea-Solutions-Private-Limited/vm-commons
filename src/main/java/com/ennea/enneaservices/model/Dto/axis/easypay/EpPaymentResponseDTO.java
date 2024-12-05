package com.ennea.enneaservices.model.Dto.axis.easypay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpPaymentResponseDTO {

    @JsonProperty("BRN")
    private long bankReferenceNumber;

    @JsonProperty("STC")
    private String status;

    @JsonProperty("RMK")
    private String remarks;

    @JsonProperty("TRN")
    private String transactionId;

    @JsonProperty("PMD")
    private String mode;

    @JsonProperty("RID")
    private String referenceId;

    @JsonProperty("VER")
    private double version;

    @JsonProperty("CID")
    private int corporateId;

    @JsonProperty("TYP")
    private String type;

    @JsonProperty("CRN")
    private String customerReferenceNumber;

    @JsonProperty("CNY")
    private String currency;

    @JsonProperty("AMT")
    private double amount;

    @JsonProperty("CKS")
    private String checksum;
}
