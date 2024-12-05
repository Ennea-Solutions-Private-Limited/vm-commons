package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class DisbursalStatusDetailDTO {

    private String customerReferenceNumber;

    private String status;

    private String responseCode;

    private String description;

}
