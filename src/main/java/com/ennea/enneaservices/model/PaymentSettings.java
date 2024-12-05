package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
public class PaymentSettings implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean paymentsEnabled;

    private boolean paymentNotificationsEnabled = true;

    private String beneficiaryCode;

    private String beneficiaryAccountNumber;

    private String beneficiaryAccountType;

    private String ifscCode;

    private String bankName;

}
 