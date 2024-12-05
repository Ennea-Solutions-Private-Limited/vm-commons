package com.ennea.enneaservices.model.Dto;


import com.ennea.enneaservices.model.User;
import lombok.Data;

@Data
public class PaymentOutstanding {

    private User supplier;

    private User customer;

    private String alias;

    private String customerPartyName;

    private String customerPartyCode;

    private double outstandingAmount;

    private String customerAddress;

    private String customerPhoneNumber;
}
