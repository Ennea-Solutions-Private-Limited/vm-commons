package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.InventoryAvailability;
import com.ennea.enneaservices.model.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class InventoryOfferMap {
    private InventoryAvailability inventory;
    private Set<Offer> offers = new HashSet<>();
}
