package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareOutstandingRequestBody {

    @JsonProperty("outstanding")
    private InvoiceDownloadDateRequestDTO invoiceDownloadRequestDatesDTO = new InvoiceDownloadDateRequestDTO();
}
