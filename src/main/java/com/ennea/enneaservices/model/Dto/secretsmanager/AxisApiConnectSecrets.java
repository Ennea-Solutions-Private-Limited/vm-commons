package com.ennea.enneaservices.model.Dto.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AxisApiConnectSecrets extends Secrets {

    private String clientSecret;

    private String clientId;

    private String channelId;

    private String encryptionKey;

    private String corporateCode;

    private String corporateAccountNumber;

    private String serviceRequestId;

    private String serviceRequestVersion;

    private String keySecret;
}

