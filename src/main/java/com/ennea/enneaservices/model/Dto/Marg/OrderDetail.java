package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {

    @JsonProperty("OrderID")
    private String orderID;

    @JsonProperty("OrderNo")
    private String orderNo;
}
