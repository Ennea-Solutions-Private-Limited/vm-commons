package com.ennea.enneaservices.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User supplier;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User representative;

    @ManyToOne
    private PaymentType paymentType;

    private double requestedAmount;

    private double paidAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private CollectionMetadata collectionMetadata;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private DisbursalMetadata disbursalMetadata;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private int walletAmount;

    @ElementCollection
    private List<PaymentInvoiceAmount> invoiceAmounts = new ArrayList<>();

    private boolean exported;

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentCustomerDetails customerDetails;
}
