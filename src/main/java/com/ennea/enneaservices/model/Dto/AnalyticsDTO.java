package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticsDTO {

    private int totalOrders;

    private int ordersReceived;

    private int ordersProcessing;

    private int ordersInvoiced;

    private int ordersCancelled;

    private double totalOrdersValue;

    private List<PartyOrderMetricsDTO> topParties;
}
