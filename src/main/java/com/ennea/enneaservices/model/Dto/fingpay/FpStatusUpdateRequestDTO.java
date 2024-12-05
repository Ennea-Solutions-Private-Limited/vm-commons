package com.ennea.enneaservices.model.Dto.fingpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FpStatusUpdateRequestDTO {

    @JsonProperty("linkId")
    private long gatewayReferenceId;

    @JsonProperty("loanAccountNumber")
    private long referenceId;

    private int clientId;

    private Double amount;

    @JsonProperty("amountExactness")
    private String amountExactness;

    @JsonProperty("expiryDate")
    private String expiryDateTime;

    @JsonProperty("created")
    private String creationDateTime;

    private String url;

    @JsonProperty("transactionDetails")
    private FpStatusUpdateTransactionDetailDTO transactionDetail;

}
