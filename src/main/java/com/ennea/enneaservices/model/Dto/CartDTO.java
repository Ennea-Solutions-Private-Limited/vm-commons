package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.CartCustomerDetails;
import com.ennea.enneaservices.model.Dto.oldDto.OrderTypeDTO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
public class CartDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserSettingsDTO supplier;

    private UserMetadataDTO customer;

    private double orderValue;

    private Set<CartInventoryDTO> inventory;

    private UserMetadataDTO representative;

    private boolean representativeOrder;

    // To be removed
    private OrderTypeDTO orderType = new OrderTypeDTO();

    private String comment;

    private double discount;

    private String minValue;

    private String paymentType;

    private CartCustomerDetails customerDetails;
}

