package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.QuotationDistrict;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuotationDTO implements Serializable {

    private long id;

    private String productName;

    private int quantity;

    private LocalDate productExpiryDate;

    private double offerRate;

    private double discount;

    private Double ptr;

    private Double pts;

    private boolean active;

    private int minimumQuantity;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private List<QuotationOrderDTO> orders = new ArrayList<>();

    private List<QuotationDistrict> districts;
}
