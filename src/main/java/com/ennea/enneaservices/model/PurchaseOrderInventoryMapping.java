package com.ennea.enneaservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PurchaseOrderInventoryMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User supplier;

    private String productName;

    private String supplierName;

    @Transient
    private transient InventoryAvailability inventoryAvailability;

    private String productCode;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private boolean isUserCreated = true;

    @JsonIgnore
    public void setDefaultTime() {
        this.creationDateTime = LocalDateTime.now();
        this.modificationDateTime = LocalDateTime.now();
    }

    @JsonIgnore
    public void setModifiedTime() {
        this.modificationDateTime = LocalDateTime.now();
    }

    @JsonIgnore
    public String getCombo() {
        return supplier.getId().toString() + ":::" + productCode;
    }
}
