package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InvoiceDownloadDateRequestDTO {

    @JsonProperty("fromdate")
    private String fromDate;

    @JsonProperty("todate")
    private String toDate;
}
