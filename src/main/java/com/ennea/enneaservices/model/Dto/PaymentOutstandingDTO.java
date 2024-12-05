package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class PaymentOutstandingDTO {

    private UserProfileDTO supplier;

    private UserProfileDTO customer;

    private String customerPartyName;

    private String partyName;

    private String customerPartyCode;

    private double outstandingAmount;

    private String customerAddress;

    private String customerPhoneNumber;
}
