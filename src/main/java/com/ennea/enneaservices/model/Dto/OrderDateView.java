package com.ennea.enneaservices.model.Dto;

import java.time.LocalDate;

public interface OrderDateView {

    LocalDate getOrderDate();

    int getOrdersCount();

    int getProductsCount();

    double getTotalValue();

    String getOrderIds();
}
