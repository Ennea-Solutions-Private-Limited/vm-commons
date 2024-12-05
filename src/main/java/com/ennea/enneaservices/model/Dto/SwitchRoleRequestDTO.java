package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SwitchRoleRequestDTO {

    @NotBlank
    private String mode;

    private long userId;

}
