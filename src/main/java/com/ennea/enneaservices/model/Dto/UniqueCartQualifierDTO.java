package com.ennea.enneaservices.model.Dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UniqueCartQualifierDTO {

    private Long supplierId;

    private String customerCode;

    private Long representativeId;
}
