package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class LedgerDTO {

    private String partyCode;

    private String partyName;

    private String address;

    private String drugLicense;

    private String phoneNumber;

    private String email;

    private String alias;

    private Double balance;

    private String salesmanCode;

    private String deliveryAgentCode;

    private String collectionAgentCode;

    private UserMetadataDTO supplier;

    private Double latitude;

    private Double longitude;
}
