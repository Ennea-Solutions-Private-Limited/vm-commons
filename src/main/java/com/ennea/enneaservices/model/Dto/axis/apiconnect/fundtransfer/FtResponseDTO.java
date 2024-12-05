package com.ennea.enneaservices.model.Dto.axis.apiconnect.fundtransfer;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "TransferPaymentResponse")
public class FtResponseDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("TransferPaymentResponseBodyEncrypted")
    private String transferPaymentResponseBodyEncrypted;
}
