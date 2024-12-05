package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class MargLedger {
    @JsonProperty("rid")
    private String rid;

    @JsonProperty("area")
    private String area;

    @JsonProperty("code")
    private String code;

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private String balance;

    @JsonProperty("pdc")
    private String pdc;

    @JsonProperty("gcode")
    private String gcode;

    @JsonProperty("opening")
    private String opening;

    @JsonProperty("Is_Deleted")
    private String isDeleted;

    @JsonProperty("phone1")
    private String phone1;

    @JsonProperty("phone2")
    private String phone2;

    @JsonProperty("phone3")
    private String phone3;

    @JsonProperty("phone4")
    private String phone4;

    @JsonProperty("email1")
    private String email1;

    @JsonProperty("email2")
    private String email2;

    @JsonProperty("email3")
    private String email3;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("MargCode")
    private String MargCode;

    @JsonProperty("GSTIN")
    private String GSTIN;

    @JsonProperty("DlNo")
    private String DlNo;

    @JsonProperty("LedgerCode")
    private String LedgerCode;
}
