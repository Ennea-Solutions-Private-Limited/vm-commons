package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private long supplierId;

    private Long customerId;

    private double requestedAmount;

    private boolean mobile;

    private int paymentTypeId;

    private List<InvoicePaymentsDTO> invoicePayments = new ArrayList<>();

    private String mode;

    private String receiptNumber;

    private LocalDate receiptDate;

    private boolean useWalletCredits;

    private String customerPartyCode;
}
