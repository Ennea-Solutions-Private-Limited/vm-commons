package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class QuotationOrder {

    @ManyToOne
    private User customer;

    private int quantity;

    private String status;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;
}
