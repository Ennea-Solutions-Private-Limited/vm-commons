package com.ennea.enneaservices.model.Dto.ProfitMaker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitMakerInventoryProductV2 {
    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("packing")
    private String packing;

    @JsonProperty("stock")
    private int stock;

    @JsonProperty("company")
    private String company;

    @JsonProperty("mrp")
    private double mrp;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("deal")
    private double deal;

    @JsonProperty("free")
    private double free;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("expiryDate")
    private String expiryDate;
}
