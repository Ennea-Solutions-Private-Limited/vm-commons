package com.ennea.enneaservices.model.Dto.Marg;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MargLiveOrderStatusOrderInfo {

    private String rid;
    private String voucher;
    @JsonProperty("OrderID")
    private String orderId;
    @JsonProperty("datesub")
    private String datesub;
    @JsonProperty("dateisu")
    private String dateisu;
    @JsonProperty("datedis")
    private String datedis;
    @JsonProperty("Is_Deleted")
    private String isDeleted;
    @JsonProperty("deleted_date")
    private String deletedDate;
}
