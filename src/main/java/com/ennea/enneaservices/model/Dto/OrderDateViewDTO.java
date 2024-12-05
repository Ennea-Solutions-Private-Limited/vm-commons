package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDateViewDTO {

    private LocalDate orderDate;

    private int ordersCount;

    private int productsCount;

    private double totalValue;

    private List<OrderDTO> orders;
}
