package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class SupplierCustomer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User supplier;

    @ManyToOne
    private User customer;

    @ManyToOne
    private RequestStatus requestStatus;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private String customerPartyName;

    private double outstandingAmount;

    private double minimumDue;

    private double creditLimit;

    private int duration;

    private String customerPartyCode;

    private boolean partyCodeEmailSent;

    private Double minOrderValue;

    private Boolean hideScheme;

    private Boolean showStock;

    private long priority;
}
