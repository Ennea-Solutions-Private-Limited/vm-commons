package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.PaymentInvoiceAmount;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreditPaymentCreateRequestDTO {

    private Long creditProviderId;

    private List<PaymentInvoiceAmount> invoicePayments = new ArrayList<>();

    private Long supplierId;


}
