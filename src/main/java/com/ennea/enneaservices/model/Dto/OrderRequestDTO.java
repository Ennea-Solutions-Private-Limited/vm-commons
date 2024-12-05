package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class OrderRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long cartId;

    private List<Long> productIds;

}

