package com.ennea.enneaservices.model.Dto.fingpay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FpPaymentRequestDTO {

    @JsonProperty("loanAccountNumber")
    private long referenceId;

    private int clientId;

    private double amount;

    @JsonProperty("expiryDate")
    private String expiryDateTime;
}
