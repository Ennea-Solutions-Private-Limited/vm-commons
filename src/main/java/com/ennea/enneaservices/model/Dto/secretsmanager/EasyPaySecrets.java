package com.ennea.enneaservices.model.Dto.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyPaySecrets extends Secrets {

    private int corporateId;

    private String checksumKey;

    private String encryptionKey;

    private String type;

    private String version;
}

