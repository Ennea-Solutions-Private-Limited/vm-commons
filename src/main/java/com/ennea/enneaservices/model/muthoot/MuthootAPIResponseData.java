package com.ennea.enneaservices.model.muthoot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MuthootAPIResponseData {

    private String message;
    @JsonProperty("eligible_amount")
    private String eligibleAmount;
    @JsonProperty("is_eligible")
    private String isEligible;
    private String url;

}
