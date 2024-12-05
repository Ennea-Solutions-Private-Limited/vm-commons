package com.ennea.enneaservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private int pincode;

    @ManyToOne
    private District district;

    @JsonIgnore
    public String getDistrictName() {
        return (district == null) ? "" : district.getName();
    }

    @JsonIgnore
    public boolean compare(Address other) {
        //
        return other != null //
               && other.getDistrictName().equals(getDistrictName()) //
               && other.getAddressLine1().equals(getAddressLine1()) //
               && other.getAddressLine2().equals(getAddressLine2()) //
               && other.getCity().equals(getCity()) //
               && (other.getPincode() == getPincode());
    }

    public String getBasicAddress() {
        String result = "";

        if(StringUtils.isNotBlank(addressLine1)){
            result += StringUtils.strip(addressLine1) + ", ";
        }

        if(StringUtils.isNotBlank(addressLine2)){
            result += StringUtils.strip(addressLine2) + ", ";
        }

        if(StringUtils.isNotBlank(city)){
            result += StringUtils.strip(city) + ", ";
        }

        if(pincode != 0 && String.valueOf(pincode).length() == 6){
            result += StringUtils.strip(String.valueOf(pincode));
        }

        return StringUtils.removeEnd(StringUtils.strip(result), ",");
    }
}
