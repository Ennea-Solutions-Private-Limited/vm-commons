package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuotationDTO implements Serializable {

    @NotEmpty
    Set<Integer> districtIds = new HashSet<>();
    private Double ptr;
    private Double pts;
    @NotBlank
    private String productName;
    private double discount;
    private long quantity;
    @NonNull
    private LocalDate productExpiryDate;
    private int minimumQuantity;
    private boolean showToDistributor;

    private boolean showToRetailer;
}
