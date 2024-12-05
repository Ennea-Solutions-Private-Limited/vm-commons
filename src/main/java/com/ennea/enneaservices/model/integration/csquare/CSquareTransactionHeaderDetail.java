package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareTransactionHeaderDetail {
    private String c2code;
    private String year;
    private String prefix;

    @JsonProperty("invNo")
    private String id;

    @JsonProperty("invoiceDate")
    private String creationDate;

    @JsonProperty("customerCode")
    private String partyCode;

    private String creditNotePrefix;
    private String creditNoteDocNo;

    @JsonProperty("creditNoteAmount")
    private String creditAmount;

    private String debitNotePrefix;
    private String debitNoteDocNo;

    @JsonProperty("debitNoteAmount")
    private String debitAmount;
    private String advanceNotePrefix;
    private String advanceNoteDocNo;
    private String advanceNoteAmount;

    @JsonProperty("invoiceTotal")
    private String value;

    @JsonProperty("provRefNo")
    private String salesOrderId;
    private String salesmanCode;
    private String gstTypeFlag;
    private String fromGstNo;

    @JsonProperty("toGstNo")
    private String gstin;

    private String cgstAmt;
    private String igstAmt;
    private String sgstAmt;
    private String cessAmt;
    private String paymentTerm;
    @JsonProperty("pendingAmt")
    private String pendingAmount;
}
