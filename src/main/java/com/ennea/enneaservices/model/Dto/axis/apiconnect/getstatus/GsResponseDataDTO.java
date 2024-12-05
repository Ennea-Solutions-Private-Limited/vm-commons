package com.ennea.enneaservices.model.Dto.axis.apiconnect.getstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GsResponseDataDTO {

    @JsonProperty("CUR_TXN_ENQ")
    private List<GsResponseTransactionDetailDTO> transactionDetails = new ArrayList<>();

    @JsonProperty("errorMessage")
    private String errorMessage;

    private String checksum;
}
