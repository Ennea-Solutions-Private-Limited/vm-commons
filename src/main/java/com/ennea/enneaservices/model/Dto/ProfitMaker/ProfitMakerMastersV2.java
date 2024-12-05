package com.ennea.enneaservices.model.Dto.ProfitMaker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitMakerMastersV2 {
    @JsonProperty("products")
    private List<ProfitMakerInventoryProductV2> products = new ArrayList<>();

    @JsonProperty("ledgers")
    private List<ProfitMakerLedger> ledgers = new ArrayList<>();

    @JsonProperty("processedOrders")
    private List<Long> OrderIds = new ArrayList<>();
}
