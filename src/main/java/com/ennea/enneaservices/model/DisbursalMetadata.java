package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class DisbursalMetadata implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private DisbursalStatus status;

    @ManyToOne
    private DisbursalGateway gateway;

    private String referenceId;

    private String bankReferenceId;

    private String comments;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

}
