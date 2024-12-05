package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.OrderStatus;

public interface AnalyticsOrder {

    Long getId();

    OrderStatus getOrderStatus();

    double getOrderValue();

    String getBusinessName();
}
