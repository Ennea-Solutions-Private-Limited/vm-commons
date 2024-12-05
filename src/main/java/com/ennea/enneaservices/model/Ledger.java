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
public class Ledger implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User supplier;

    private String partyCode;

    private String partyName;

    private String alias;

    private String address;

    private String phoneNumber;

    private String drugLicense;

    private String gstin;

    private Double balance;

    private String bank;

    private String branch;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private String emailId;

    private LocalDate drugLicenseExpiry;

    private boolean active;

    private String salesmanCode;

    private String deliveryAgentCode;

    private String collectionAgentCode;

    private Double latitude;

    private Double longitude;
}
