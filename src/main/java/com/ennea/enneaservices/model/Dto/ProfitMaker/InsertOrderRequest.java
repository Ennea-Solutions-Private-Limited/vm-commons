package com.ennea.enneaservices.model.Dto.ProfitMaker;

import lombok.Getter;

import java.util.List;

@Getter
public class InsertOrderRequest {

    private String businessName;

    private String uuid;

    private String key;

    private String version;

    private List<Long> orderIds;
}
