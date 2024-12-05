package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class ErpCredentialsDTO {

    private String username;

    private String password;

    private ErpDTO erp;

    private String externalKey;

    private String decryptionKey;

    private String salesManId;

    private String salesManUserId;

    private String systemUUID;
}
