package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
