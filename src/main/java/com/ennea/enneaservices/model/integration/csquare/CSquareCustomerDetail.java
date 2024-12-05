package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareCustomerDetail {

    @JsonProperty("code")
    private String code;

    @JsonProperty("custName")
    private String name;

    @JsonProperty("c_drug_licence_no_1")
    private String drugLicense1;

    @JsonProperty("c_drug_licence_no_2")
    private String drugLicense2;

    @JsonProperty("c_gst_no")
    private String gstin;

    private String alterCode;

    private String emailId;

    private String city;

    @JsonProperty("c_state")
    private String state;

    private String pincode;

    @JsonProperty("dl_exp_date")
    private String drugLicenseExpiryDate;

    //    private String code;
//    private String custName;
    private String add1;
    private String add2;
    private String add3;
    private String creditDays;
    private String actLock;
    private String autoLock;
//    private String c_drug_licence_no_1;

    //    private String c_gst_no;
//    private String c_state;
    private String contactPerson;
    private String maxCreditAmt;
    private String smanCode;
    private String smanName;
    private String phone1;
    private String phone2;
    private String mobileNo;
}
