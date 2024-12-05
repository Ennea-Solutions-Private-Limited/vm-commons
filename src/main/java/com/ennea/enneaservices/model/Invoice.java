package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Invoice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NotNull(message = "Unable to find supplier in the transaction")
    private User supplier;

    @ManyToOne
    private User customer;

    private String invoiceNumber;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private Double netValue;

    private Double grossValue;

    private Double pendingAmount;

    @ElementCollection
    private List<InvoiceDetail> details = new ArrayList<>();

    private String partyName;

    private String partyCode;

    private String phoneNumber;

    private String gstin;

    private String drugLicense;

    private String comment;

    private Double tax;

    private Double discount;

    private Double creditAmount;

    private Double debitAmount;

    private String paymentTerm;

    private boolean paid;

    private LocalDate invoiceModificationDate;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private int updateCount;

    private boolean cancelled;

    private String shippingAddress;

    private String billingAddress;

    private String salesOrderId;

    private Double invoiceAmount;

    private Double billAmount;

    private Double adjustedAmount;

    private Double totalPayable;

    @ManyToOne
    private InvoiceStatus invoiceStatus;

    private Integer deliveryBoxes;

    private LocalDateTime deliveredDateTime;

    private String updateId;

    private String division;

    @OneToOne
    private WhatsappStatus whatsappStatus;

    @OneToOne
    private EmailStatus emailStatus;

    private boolean creditRequestRaised;

}
