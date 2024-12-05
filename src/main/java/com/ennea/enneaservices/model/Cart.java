package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Cart implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User supplier;

    @ManyToOne
    private User customer;

    private double orderValue;

    @ElementCollection
    @Cascade(value = CascadeType.ALL)
    private Set<CartInventory> inventory;

    @ManyToOne
    private User representative;

    private boolean representativeOrder;

    private String comment;

    private double discount;

    private String paymentType;

    @Cascade(value = CascadeType.ALL)
    @OneToOne
    private CartCustomerDetails customerDetails;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

}

