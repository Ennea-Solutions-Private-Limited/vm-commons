package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.PaymentCustomerDetails;
import com.ennea.enneaservices.model.PaymentInvoiceAmount;
import com.ennea.enneaservices.model.PaymentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserMetadataDTO supplier;

    private UserMetadataDTO customer;

    private UserMetadataDTO representative;

    private double requestedAmount;

    private double paidAmount;

    private CollectionMetadataDTO collectionMetadata;

    private String disbursalStatus;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private PaymentType paymentType;

    private int walletAmount;

    private List<PaymentInvoiceAmount> invoiceAmounts = new ArrayList<>();

    private PaymentCustomerDetails customerDetails;
}
