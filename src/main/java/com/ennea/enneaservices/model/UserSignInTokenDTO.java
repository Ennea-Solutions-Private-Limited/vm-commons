package com.ennea.enneaservices.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInTokenDTO {

    @NotBlank
    private String token;
}