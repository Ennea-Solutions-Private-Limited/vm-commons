package com.ennea.enneaservices.model.Dto.axis.apiconnect.fundtransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FtRequestInvoiceDetailDTO {

    @JsonProperty("invoiceAmount")
    private String invoiceAmount;

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;

    @JsonProperty("invoiceDate")
    private String invoiceDate;

    @JsonProperty("cashDiscount")
    private String cashDiscount;

    @JsonProperty("tax")
    private String tax;

    @JsonProperty("netAmount")
    private String netAmount;

    @JsonProperty("invoiceInfo1")
    private String info1;

    @JsonProperty("invoiceInfo2")
    private String info2;

    @JsonProperty("invoiceInfo3")
    private String info3;

    @JsonProperty("invoiceInfo4")
    private String info4;

    @JsonProperty("invoiceInfo5")
    private String info5;
}
