package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.PaymentInvoiceAmount;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPPaymentDTO {

    private long id;

    private String partyCode;

    private String partyName;

    private double amount;

    private String mode;

    private String receiptNumber;

    private LocalDate receiptDate;

    private String status;

    private List<PaymentInvoiceAmount> invoiceAmounts;
}

