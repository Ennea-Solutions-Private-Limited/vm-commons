package com.ennea.enneaservices.model.porter;

import com.ennea.enneaservices.model.Dto.secretsmanager.Secrets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PorterSecrets extends Secrets {

    private String endpoint;
    private String authKey;

}
