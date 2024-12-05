package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class PaymentInvoiceAmount {

    String invoiceNumber;

    double amount;

    LocalDate invoiceDate;
}
