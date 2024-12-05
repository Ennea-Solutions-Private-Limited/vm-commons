package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OfferCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long productId;

    @NotBlank
    private String description;

    private long userGroupId;

    private LocalDate endDate;

    private LocalDate startDate;

}
