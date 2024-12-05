package com.ennea.enneaservices.model.Dto.Marg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MargLiveOrderStatusOrderMain {

    private String rid;
    @JsonProperty("Amount")
    private String amount;

    private String code;
    @JsonProperty("companyid")
    private String companyId;

    private String dt;

    private String profit;

    @JsonProperty("smccode")
    private String smcCode;

    private String vcn;

    private String voucher;

    @JsonProperty("shopcode")
    private String shopCode;

    @JsonProperty("area")
    private String area;

    @JsonProperty("OrderNo")
    private String orderNo;

    @JsonProperty("Billfmt")
    private String billFmt;

    @JsonProperty("Salesmemo")
    private String salesMemo;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Shortage")
    private String shortage;


}
