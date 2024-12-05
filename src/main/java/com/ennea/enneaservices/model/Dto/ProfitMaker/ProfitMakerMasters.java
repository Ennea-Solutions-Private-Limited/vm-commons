package com.ennea.enneaservices.model.Dto.ProfitMaker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitMakerMasters {
    @JsonProperty("products")
    private List<ProfitMakerInventoryProduct> products;

    @JsonProperty("ledgers")
    private List<ProfitMakerLedger> ledgers;

    @JsonProperty("processedOrders")
    private List<Long> OrderIds;
}
