package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OfferDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private InventoryAvailabilityDTO inventoryAvailability;

    private UserMetadataDTO supplier;

    private UserGroupDTO userGroup;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean activeStatus;

}
