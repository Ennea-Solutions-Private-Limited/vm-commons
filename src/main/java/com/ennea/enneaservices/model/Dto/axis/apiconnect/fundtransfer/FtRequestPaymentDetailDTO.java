package com.ennea.enneaservices.model.Dto.axis.apiconnect.fundtransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonRootName(value = "TransferPaymentRequest")
public class FtRequestPaymentDetailDTO {

    @JsonProperty("txnPaymode")
    private String mode;

    @JsonProperty("custUniqRef")
    private String customerUniqueReferenceId;

    @JsonProperty("corpAccNum")
    private String corporateAccountNumber;

    @JsonProperty("valueDate")
    private String date;

    @JsonProperty("txnAmount")
    private String amount;

    @JsonProperty("beneLEI")
    private String beneLEI;

    @JsonProperty("beneName")
    private String beneficiaryName;

    @JsonProperty("beneCode")
    private String beneficiaryCode;

    @JsonProperty("beneAccNum")
    private String beneficiaryAccountNumber;

    @JsonProperty("beneAcType")
    private String beneficiaryAccountType;

    @JsonProperty("beneAddr1")
    private String addressLine1;

    @JsonProperty("beneAddr2")
    private String addressLine2;

    @JsonProperty("beneAddr3")
    private String addressLine3;

    @JsonProperty("beneCity")
    private String city;

    @JsonProperty("beneState")
    private String state;

    @JsonProperty("benePincode")
    private String pincode;

    @JsonProperty("beneIfscCode")
    private String ifscCode;

    @JsonProperty("beneBankName")
    private String bankName;

    @JsonProperty("baseCode")
    private String baseCode;

    @JsonProperty("chequeNumber")
    private String chequeNumber;

    @JsonProperty("chequeDate")
    private String chequeDate;

    @JsonProperty("payableLocation")
    private String payableLocation;

    @JsonProperty("printLocation")
    private String printLocation;

    @JsonProperty("beneEmailAddr1")
    private String email;

    @JsonProperty("beneMobileNo")
    private String mobileNumber;

    @JsonProperty("productCode")
    private String productCode;

    @JsonProperty("txnType")
    private String type;

    @JsonProperty("invoiceDetails")
    private List<FtRequestInvoiceDetailDTO> invoiceDetails = new ArrayList<>();

    @JsonProperty("enrichment1")
    private String enrichment1;

    @JsonProperty("enrichment2")
    private String enrichment2;

    @JsonProperty("enrichment3")
    private String enrichment3;

    @JsonProperty("enrichment4")
    private String enrichment4;

    @JsonProperty("enrichment5")
    private String enrichment5;

    @JsonProperty("senderToReceiverInfo")
    private String senderToReceiverInfo;
}
