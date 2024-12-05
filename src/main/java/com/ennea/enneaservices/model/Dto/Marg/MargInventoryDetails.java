package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MargInventoryDetails {
    @JsonProperty("pro_N")
    private List<MargInventoryProduct> proN;

    @JsonProperty("pro_U")
    private List<MargInventoryProduct> proU;

    @JsonProperty("pro_R")
    private List<MargInventoryProduct> proR;

    @JsonProperty("Index")
    private String index;
    @JsonProperty("Datastatus")
    private String datastatus;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("DateTime")
    private String dateTime;
}
