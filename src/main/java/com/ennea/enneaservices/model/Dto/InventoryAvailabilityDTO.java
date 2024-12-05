package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryAvailabilityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserSettingsDTO supplier;

    private String productName;

    private String packing;

    private int availability;

    private String available;

    private String productCode;

    private double mrp;

    //Rename to rate, rmeove the mrp property
    private double ptr;

    private double rate;

    private double pts;

    private String division;

    private LocalDate expiryDate;

    private List<SchemeDTO> schemes = new ArrayList<>();

    private boolean activeStatus;

    private boolean hiddenStatus;

    private double taxRate;

    private Integer threshold;

    private int deal;

    private int free;
}
