package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OfferUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private LocalDate endDate;

    private boolean activeStatus;

    @NotBlank
    private String description;

    private long userGroupId;

    private long productId;
}
