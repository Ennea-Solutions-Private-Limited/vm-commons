package com.ennea.enneaservices.model.integration.csquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CSquareCommonRequestBody {
    private String customerCode;

    @JsonProperty("lDate")
    private String lDate;
}
