package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LogisticsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User source;

    @ManyToOne
    private User destination;

    private String provider;

    private String providerOrderId;

    private Long estimatedPrice;

    private Long finalPrice;

    private String status;

    private String trackingLink;

    @ManyToOne
    private User createdBy;

    private LocalDateTime creationDatetime;

    private LocalDateTime orderCreationDatetime;

    private String extraInfo;

    public Double getEstimatedPriceInRupees() {
        return (double) (estimatedPrice / 100);
    }

}
