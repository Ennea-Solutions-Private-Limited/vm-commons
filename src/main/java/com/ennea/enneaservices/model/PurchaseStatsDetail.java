package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PurchaseStatsDetail {

    private Integer purchaseQuantity;

    private Integer purchaseSchemeQuantity;

    private Double purchaseRate;

    private String supplierName;

    private String email;

    private String phoneNumber;
}
