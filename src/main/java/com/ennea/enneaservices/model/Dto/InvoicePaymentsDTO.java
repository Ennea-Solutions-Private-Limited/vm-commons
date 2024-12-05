package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoicePaymentsDTO {
    private String invoiceNumber;

    private double invoiceAmount;

    private LocalDate invoiceDate;

}
