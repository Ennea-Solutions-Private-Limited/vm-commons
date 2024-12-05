package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User supplier;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private boolean active;

    private String productName;

    private int quantity;

    private LocalDate productExpiryDate;

    private double discount;

    @ElementCollection
    @NotEmpty(message = "Districts cannot be empty")
    private List<QuotationDistrict> districts;

    private Double ptr;

    private Double pts;

    private int minimumQuantity;

    @ElementCollection
    private List<QuotationOrder> orders = new ArrayList<>();

    private boolean showToDistributor;

    private boolean showToRetailer;
}