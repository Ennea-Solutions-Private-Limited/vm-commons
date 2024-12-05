package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ERPAddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String pincode;

    private String district;

    private String state;

    private String country;

    public String toString() {
        StringBuilder b = new StringBuilder();
        if(this.getAddressLine1() != null){
            b.append(this.getAddressLine1()).append(", ");
        }
        if(this.getAddressLine2() != null){
            b.append(this.getAddressLine2()).append(", ");
        }
        if(this.getCity() != null){
            b.append(this.getCity()).append(", ");
        }
        if(this.getDistrict() != null){
            b.append(this.getDistrict()).append(", ");
        }
        if(this.getState() != null){
            b.append(this.getState()).append(", ");
        }
        if(this.getCountry() != null){
            b.append(this.getCountry()).append(", ");
        }
        if(this.getPincode() != null){
            b.append(this.getPincode());
        }
        return b.toString();
    }
}
