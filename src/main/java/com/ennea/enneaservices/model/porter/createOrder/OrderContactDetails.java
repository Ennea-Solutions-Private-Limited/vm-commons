package com.ennea.enneaservices.model.porter.createOrder;

import lombok.Data;

@Data
public class OrderContactDetails {
    // Name of the contact person
    private String name;

    // Phone number of the contact person (including country code)
    private String phoneNumber;
}
