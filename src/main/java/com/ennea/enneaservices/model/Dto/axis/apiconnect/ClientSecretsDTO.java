package com.ennea.enneaservices.model.Dto.axis.apiconnect;

import lombok.Data;

@Data
public class ClientSecretsDTO {

    private String clientSecret;

    private String clientId;

    private String encryptionKey;

    private String keySecret;
}
