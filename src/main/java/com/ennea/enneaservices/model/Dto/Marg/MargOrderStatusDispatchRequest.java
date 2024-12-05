package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MargOrderStatusDispatchRequest {

    @JsonProperty("CompanyCode")
    private String CompanyCode;
    @JsonProperty("MargID")
    private String MargID;
    @JsonProperty("Datetime")
    private String Datetime;
    @JsonProperty("index")
    private String index;

    @JsonProperty("SalesmanID")
    private String SalesmanID;

    @JsonProperty("Type")
    private String Type;


}
