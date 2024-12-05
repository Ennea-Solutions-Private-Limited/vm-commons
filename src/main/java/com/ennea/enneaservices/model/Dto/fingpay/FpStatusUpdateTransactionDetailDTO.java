package com.ennea.enneaservices.model.Dto.fingpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FpStatusUpdateTransactionDetailDTO {

    private String status;

    private LocalDate paymentDate;

    @JsonProperty("paymentAmount")
    private Double amount;

    @JsonProperty("paymentMode")
    private String mode;

    private String upiId;

}
