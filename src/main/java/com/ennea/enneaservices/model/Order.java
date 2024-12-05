package com.ennea.enneaservices.model;

import com.ennea.enneaservices.model.Dto.OrderMetadataDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "`order`")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Unable find supplier in the order")
    private User supplier;

    @ManyToOne
    private User customer;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderCustomerDetails customerDetails;

    @ManyToOne
    private OrderStatus orderStatus;

    private double orderValue;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private LocalDateTime deliveredDateTime;

    private boolean representativeOrder = false;

    @ManyToOne
    private User representative;

    private String referenceId;

    @ElementCollection
    @NotEmpty(message = "Order details cannot be empty")
    private List<Details> details = new ArrayList<>();

    private boolean rewardProcessed;

    private String comment;

    private String paymentType;

    private double discount;

    @Column(name = "s3_reference")
    private String s3Reference;

    private transient List<Action> actions = new ArrayList<>();

    private transient boolean isEditable;

    private transient String customerPartyName;

    private transient OrderMetadataDTO orderMetadataDTO;
}
