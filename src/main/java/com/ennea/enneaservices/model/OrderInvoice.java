package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class OrderInvoice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    //    @Lob
    private String invoiceResponse;

    private long distributorId;

    private String dateTime;

    private String invoiceOrderStatus;

    private LocalDate invoiceDateCreated;

    private LocalDate invoiceDateUpdated;

    private boolean processed;

    private String orderMainNumber;


}
