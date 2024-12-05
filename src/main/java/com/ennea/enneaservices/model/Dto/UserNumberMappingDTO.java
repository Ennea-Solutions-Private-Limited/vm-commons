package com.ennea.enneaservices.model.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserNumberMappingDTO {

    private String name;

    private Long phoneNumber;

    private Long sessionCount;

    private boolean isPrimary;

}
