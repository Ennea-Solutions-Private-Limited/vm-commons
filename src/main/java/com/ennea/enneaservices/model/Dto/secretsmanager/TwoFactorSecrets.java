package com.ennea.enneaservices.model.Dto.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoFactorSecrets extends Secrets {

    private String apiKey;

    private String header;
}

