package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MargInventoryProduct {
    @JsonProperty("rid")
    private String rid;

    @JsonProperty("catcode")
    private String catcode;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("stock")
    private String stock;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("company")
    private String company;

    @JsonProperty("shopcode")
    private String shopcode;

    @JsonProperty("MRP")
    private String MRP;

    @JsonProperty("Rate")
    private String Rate;

    @JsonProperty("Deal")
    private String Deal;

    @JsonProperty("Free")
    private String Free;

    @JsonProperty("PRate")
    private String purchaseRate;

    @JsonProperty("Is_Deleted")
    private String Is_Deleted;

    @JsonProperty("curbatch")
    private String curbatch;

    @JsonProperty("exp")
    private String exp;

    @JsonProperty("gcode")
    private String gcode;

    @JsonProperty("MargCode")
    private String MargCode;

    @JsonProperty("Conversion")
    private String Conversion;

    @JsonProperty("Salt")
    private String Salt;

    @JsonProperty("ENCODE")
    private String ENCODE;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("Gcode6")
    private String Gcode6;

    @JsonProperty("ProductCode")
    private String ProductCode;
}
