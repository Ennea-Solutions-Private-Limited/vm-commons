package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.CartCustomerDetails;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class CartUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "Cart details cannot be null")
    private List<CartDetailDTO> cartDetails;

    private String comment;

    private double discount;

    private String paymentType;

    private CartCustomerDetails cartCustomerDetails;
}

