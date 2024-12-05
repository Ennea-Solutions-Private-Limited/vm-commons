package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MargLiveOrderStatusDetails {

    @JsonProperty("OrderMain")
    List<MargLiveOrderStatusOrderMain> orderMain = new ArrayList<>();

    @JsonProperty("OrderInfo")
    List<MargLiveOrderStatusOrderInfo> orderInfo = new ArrayList<>();


    @JsonProperty("Index")
    private String index;

    @JsonProperty("Datastatus")
    private String dataStatus;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Datetime")
    private String dateTime;
}
