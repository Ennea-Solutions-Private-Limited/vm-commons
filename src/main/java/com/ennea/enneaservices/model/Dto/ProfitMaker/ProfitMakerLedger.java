package com.ennea.enneaservices.model.Dto.ProfitMaker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitMakerLedger {
    @JsonProperty("rid")
    private int rid;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("gstin")
    private String gstin;

    @JsonProperty("dlNo")
    private String dlNo;

    @JsonProperty("ledgerCode")
    private String ledgerCode;
}
