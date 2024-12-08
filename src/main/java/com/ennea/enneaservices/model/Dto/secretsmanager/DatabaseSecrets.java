package com.ennea.enneaservices.model.Dto.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseSecrets extends Secrets {

    private String username;

    private String password;

    private String host;

    private String port;

    private String dbInstanceIdentifier;

    private String engine;

    private String schema;
}
