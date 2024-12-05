package com.ennea.enneaservices.model.Dto.Printer;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class PrintSummary {

    private Long id;

    private String partyName;

    private Long uuid;

    private String city;

    private Long phoneNumber;

    private String orderDate;

    private Map<String, String> orderDetails;
}
