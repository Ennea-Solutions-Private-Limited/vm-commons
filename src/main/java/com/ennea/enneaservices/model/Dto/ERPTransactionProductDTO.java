package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPTransactionProductDTO {

    private String productCode;

    private String productName;

    private String packing;

    private String manufacturer;

    private String batch;

    private String expiryDate;

    private float quantity;

    private float free;

    @JsonProperty("ptr")
    @JsonAlias("rate")
    private double ptr;

    @JsonProperty("netRate")
    @JsonAlias("actualRate")
    private double netRate;

    private double mrp;
    private double discount;

    private double tax;

    private String hsnCode;
}
