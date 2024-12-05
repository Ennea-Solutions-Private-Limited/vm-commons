package com.ennea.enneaservices.model.Dto.axis.apiconnect.fundtransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FtRequestBodyDTO {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("corpCode")
    private String corporateCode;

    @JsonProperty("paymentDetails")
    private List<FtRequestPaymentDetailDTO> paymentDetails = new ArrayList<>();

    private String checksum;
}
