package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPTransactionPaymentUpdateDTO {

    @NotBlank
    private String id;

    private double pendingAmount;

    @NotBlank
    private String creationDate;

    @Transient
    public LocalDate getParsedCreationDate() {
        return DateTimeUtils.parseDate(this.getCreationDate(), "yyyy-MM-dd");
    }
}
