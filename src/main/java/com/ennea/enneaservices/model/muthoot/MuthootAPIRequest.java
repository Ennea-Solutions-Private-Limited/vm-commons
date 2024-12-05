package com.ennea.enneaservices.model.muthoot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MuthootAPIRequest {

    @JsonProperty("customerID")
    private String uuid;
    @JsonProperty("mobile")
    private String phoneNumber;
    @JsonProperty("avg_gmv_last_3months")
    private Integer last3MonthsAvg;
    @JsonProperty("anchor_invoice_percentage")
    private Integer halfYearlyPercentage;
    @JsonProperty("net_gmv_last_12months")
    private Integer last12MonthsAvg;
    @JsonProperty("vintage_months")
    private Long customerAge;

}
