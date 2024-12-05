package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPTransactionDTO {

    //InvoiceNumber in the Invoice
    @NotBlank
    private String id;

    //invoiceDate
    @NotBlank
    private String creationDate;

    //invoiceModificationDate
    private String modificationDate;

    @NotBlank
    private String partyName;

    @NotBlank
    private String partyCode;

    private double netValue;

    private String paymentTerm;

    private double pendingAmount;

    private List<ERPTransactionProductDTO> products = new ArrayList<>();

    private String salesOrderId;

    private boolean cancelled;
}
