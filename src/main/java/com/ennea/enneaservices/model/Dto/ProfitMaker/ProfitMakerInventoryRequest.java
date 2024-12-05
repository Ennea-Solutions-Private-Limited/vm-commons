package com.ennea.enneaservices.model.Dto.ProfitMaker;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProfitMakerInventoryRequest {
    @NotBlank
    private String businessName;

    @NotBlank
    private String inventoryData;
}
