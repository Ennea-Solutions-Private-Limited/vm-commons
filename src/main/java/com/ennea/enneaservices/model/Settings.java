package com.ennea.enneaservices.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
public class Settings implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean displayActualFigures;

    private boolean hidePtr;

    private boolean usingHideSchemes;

    private boolean automaticApproval;

    private boolean automaticInvoicing;

    private boolean usingOrderMessages;

    private boolean usingHalfSchemes;

    private boolean skipAvailabilityCheck;

    private boolean usingValuemediFreeQuantity;

    private boolean manualInventoryUpload;

    private boolean globalSearch;

    private String representativePrefix;

    private boolean computeOutstandingBalance;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private PaymentSettings paymentSettings;

    private boolean couponBlocked;

    private String parserConfig;

    private String sendOrderEmail;

    private boolean usingSaleMode;

    private boolean usingRepresentativeOffline;

    private boolean autoMapProducts;
}

 