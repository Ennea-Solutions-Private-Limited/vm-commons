package com.ennea.enneaservices.model.Dto.fingpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FpPaymentResponseDTO {

    @JsonProperty("linkId")
    private long gatewayReferenceId;

    @JsonProperty("loanAccountNumber")
    private long referenceId;

    private long clientId;

    private double amount;

    @JsonProperty("expiryDate")
    private String expiryDateTime;

    @JsonProperty("created")
    private String creationDateTime;

    private String url;

    private String error;
}
