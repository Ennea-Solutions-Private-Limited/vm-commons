package com.ennea.enneaservices.model.Dto.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuthootSecrets extends Secrets {

    private String apiKey;

    private String sourceEntityId;
}
