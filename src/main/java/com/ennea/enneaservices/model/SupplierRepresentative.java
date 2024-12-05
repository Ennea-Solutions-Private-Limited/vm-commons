package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class SupplierRepresentative implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User supplier;

    @ManyToOne
    private User representative;

    @ElementCollection
    private Set<String> salesmanCodes = new HashSet<>();

    @ManyToOne
    private RequestStatus requestStatus;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private boolean isMaster;

}
