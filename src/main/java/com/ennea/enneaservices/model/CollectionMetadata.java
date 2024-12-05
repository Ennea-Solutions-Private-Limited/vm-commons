package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class CollectionMetadata implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private CollectionStatus status;

    @ManyToOne
    private CollectionGateway gateway;

    private String referenceId;

    private String gatewayReferenceId;

    private long bankReferenceId;

    private LocalDate receiptDate;

    private String comments;

    private String mode;

    private String url;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

}
