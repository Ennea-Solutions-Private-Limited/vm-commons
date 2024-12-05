package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartCustomerDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private String partyCode;
    private String emailId;
    private Long phoneNumber;

    public CartCustomerDetails(User u) {
        businessName = u.getBusinessName();
        emailId = u.getEmailId();
        phoneNumber = u.getPhoneNumber();
    }
}
