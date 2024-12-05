package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Scheme;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class ERPProductComparator {

    private String productName;

    private String packing;

    private int availability;

    private String productCode;

    private double mrp;

    private double ptr;

    private String division;

    private LocalDate expiryDate;

    @ElementCollection
    private List<Scheme> schemes = new ArrayList<>();

    private boolean activeStatus;

    private String alias;

    private double pts;

    private String casePacking;

    private double taxRate;
}
