package com.ennea.enneaservices.model.Dto.axis.apiconnect.fundtransfer;

import com.ennea.enneaservices.model.Dto.axis.apiconnect.SubHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName(value = "TransferPaymentRequest")
public class FtRequestDTO {

    @JsonProperty("SubHeader")
    private SubHeaderDTO subHeaderDTO;

    @JsonProperty("TransferPaymentRequestBodyEncrypted")
    private String transferPaymentRequestBodyEncrypted;
}
