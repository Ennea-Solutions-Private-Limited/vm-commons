package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PurchaseStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    private String productCode;

    private String productName;

    private String companyName;

    private String pack;

    private Integer stock;

    private Integer qtyPerBox;

    private Integer qtyPerCase;

    private Integer salesQty;

    private Integer purchaseQty;

    @ElementCollection
    private List<PurchaseStatsDetail> details = new ArrayList<>();

    private LocalDate transactionDate;

    private LocalDateTime creationDateTime;

    public PurchaseStats(User user) {
        this.user = user;
        this.creationDateTime = LocalDateTime.now();
    }
}
