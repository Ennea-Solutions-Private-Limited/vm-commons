package com.ennea.enneaservices.model.Dto.ProfitMaker;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProfitMakerMastersRequest {

    private String businessName;

    private String uuid;

    private String key;

    @NotBlank
    private String mastersData;

    private String version;
}
