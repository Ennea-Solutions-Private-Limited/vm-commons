package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpFormDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String contactPerson;

    private String emailId;

    private long phoneNumber;

    private int pinCode;

    private String promoCode;

    private String gstin;

    private LocationDto location;
}
