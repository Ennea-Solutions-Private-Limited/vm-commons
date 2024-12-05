package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDetails {

    @JsonProperty("OrderDetails")
    private List<OrderDetail> orderDetails;

    @JsonProperty("CustomerID")
    private String customerID;

    @JsonProperty("UserID")
    private String userID;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("PCode")
    private String pCode;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Message")
    private String message;
}
