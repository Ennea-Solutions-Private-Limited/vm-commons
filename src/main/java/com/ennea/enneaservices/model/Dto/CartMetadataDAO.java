package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Cart;
import com.ennea.enneaservices.model.CartCustomerDetails;
import com.ennea.enneaservices.model.Ledger;
import com.ennea.enneaservices.model.User;
import lombok.Data;
import org.apache.commons.collections4.map.MultiKeyMap;

import java.util.HashMap;
import java.util.Map;

@Data
public class CartMetadataDAO {

    private User user;

    private MultiKeyMap supplierIdAndCustomerIdToSupplierCustomer = new MultiKeyMap<>();

    private MultiKeyMap supplierIdAndRepresentativeIdToSupplierRepresentative = new MultiKeyMap<>();

    private Map<Long, Cart> cartIdToCart = new HashMap<>();

    private boolean representativeUpdate = false;

    private User representative;

    private User customer;

    private Ledger customerLedger;

    private Map<Long, UserPreferenceSettingsDTO> supplierIdToUserPreferenceSettingsMap = new HashMap<>();

    private CartCustomerDetails cartCustomerDetails;

}

