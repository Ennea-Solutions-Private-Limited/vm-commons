package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MargMastersRequest {
    @JsonProperty("CompanyCode")
    private String CompanyCode;
    @JsonProperty("MargID")
    private String MargID;
    @JsonProperty("Datetime")
    private String Datetime;
    @JsonProperty("index")
    private String index;
}
