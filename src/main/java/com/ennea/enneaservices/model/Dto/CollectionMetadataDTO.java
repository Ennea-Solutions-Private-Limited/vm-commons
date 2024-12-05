package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CollectionMetadataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String status;

    private String mode;

    private String url;

    private String receiptNumber;

    private LocalDate receiptDate;

}
