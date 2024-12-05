package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RepresentativeDetailsDTO {

    private Long representativeId;

    @NotBlank
    private String name;

    @NonNull
    private Long phoneNumber;

    private String code;

    private String usernameSuffix;
}
