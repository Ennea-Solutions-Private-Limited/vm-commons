package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class InventoryAvailability implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User supplier;

    private String productName;

    private String packing;

    private int availability;

    private String productCode;

    private double mrp;

    private double ptr;

    private String division;

    private LocalDate expiryDate;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    @ElementCollection
    private List<Scheme> schemes = new ArrayList<>();

    private boolean activeStatus;

    private boolean hiddenStatus;

    private String alias;

    private double pts;

    private String casePacking;

    private double taxRate;

    private int deal;

    private String searchPrefixes;

    private int free;

    private Integer threshold;

    private String internalGroup;

    @Transient
    private transient CartInventory cartInventory;
}
