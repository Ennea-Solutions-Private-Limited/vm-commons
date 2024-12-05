package com.ennea.enneaservices.model.Dto.axis.easypay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EpPaymentRequestDTO {

    @JsonProperty("VER")
    private double version;

    @JsonProperty("CID")
    private String corporateId;

    @JsonProperty("TYP")
    private String type;

    @JsonProperty("RID")
    private String referenceId;

    @JsonProperty("CRN")
    private String customerReferenceNumber;

    @JsonProperty("CNY")
    private String currency;

    @JsonProperty("AMT")
    private long amount;

    @JsonProperty("RTU")
    private String callBackURL;

    @JsonProperty("PPI")
    private String ppi;

    @JsonProperty("RE1")
    private String re1;

    @JsonProperty("RE2")
    private String re2;

    @JsonProperty("RE3")
    private String re3;

    @JsonProperty("RE4")
    private String re4;

    @JsonProperty("RE5")
    private String re5;

    @JsonProperty("CKS")
    private String checksum;
}
