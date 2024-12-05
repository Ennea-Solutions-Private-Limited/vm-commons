package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignInResponseDTO {

    private String accessToken;
    private String refreshToken;
    private String username;
    private RoleDTO role;
    private String businessName;
    private String currentMode;
    private boolean primaryUser;

}
