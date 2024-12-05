package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignUpResponseDTO {

    private String message;

    private String phoneNumber;

    public UserSignUpResponseDTO(String message) {
        this.message = message;
    }
}
