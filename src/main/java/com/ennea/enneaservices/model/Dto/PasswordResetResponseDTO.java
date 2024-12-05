package com.ennea.enneaservices.model.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordResetResponseDTO {

    private String username;

}
