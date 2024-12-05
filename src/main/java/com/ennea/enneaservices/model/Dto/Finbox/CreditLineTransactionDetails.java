package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

import java.util.List;

@Data
public class CreditLineTransactionDetails {

    private Double gst;
    private Double disbursalAmount;
    private String lenderName;
    private String disbursalUTR;
    private Double amount;
    private String createdAt;
    private String txnStatus;
    private Double processingFee;
    private String emiCalculationMethod;
    private Double subventionAmount;
    private Double interest;
    private String invoiceNo;
    private String partnerTxnID;
    private String txnID;
    private List<CreditLineTransactionEmiDetails> emis;

}
