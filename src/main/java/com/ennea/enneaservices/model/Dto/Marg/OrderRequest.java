package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRequest {

    @JsonProperty("OrderID")
    private String OrderID;

    @JsonProperty("OrderNo")
    private String OrderNo;

    @JsonProperty("CustomerID")
    private String CustomerID;

    @JsonProperty("MargID")
    private String MargID;

    @JsonProperty("Type")
    private String Type;

    @JsonProperty("Sid")
    private String Sid;

    @JsonProperty("ProductCode")
    private String ProductCode;

    @JsonProperty("Quantity")
    private String Quantity;

    @JsonProperty("Free")
    private String Free;

    @JsonProperty("Lat")
    private String Lat;

    @JsonProperty("Lng")
    private String Lng;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("GpsID")
    private String GpsID;

    @JsonProperty("UserType")
    private String UserType;

    @JsonProperty("Points")
    private String Points;

    @JsonProperty("Discounts")
    private String Discounts;

    @JsonProperty("Transport")
    private String Transport;

    @JsonProperty("Delivery")
    private String Delivery;

    @JsonProperty("Bankname")
    private String Bankname;

    @JsonProperty("BankAdd1")
    private String BankAdd1;

    @JsonProperty("BankAdd2")
    private String BankAdd2;

    @JsonProperty("shipname")
    private String shipname;

    @JsonProperty("shipAdd1")
    private String shipAdd1;

    @JsonProperty("shipAdd2")
    private String shipAdd2;

    @JsonProperty("shipAdd3")
    private String shipAdd3;

    @JsonProperty("paymentmode")
    private String paymentmode;

    @JsonProperty("paymentmodeAmount")
    private String paymentmodeAmount;

    @JsonProperty("payment_remarks")
    private String payment_remarks;

    @JsonProperty("order_remarks")
    private String order_remarks;

    @JsonProperty("CustMobile")
    private String CustMobile;

    @JsonProperty("CompanyCode")
    private String CompanyCode;

    @JsonProperty("OrderFrom")
    private String OrderFrom;
}
