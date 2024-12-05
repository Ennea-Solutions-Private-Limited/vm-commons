package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Cart;
import com.ennea.enneaservices.model.SupplierRepresentative;
import com.ennea.enneaservices.model.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderMetadataDTO {

    private User customer;

    private User representative;

    private boolean representativeUpdate;

    private Map<Long, SupplierRepresentative> supplierRepresentativeMap = new HashMap<>();

    private Cart cart;

}

