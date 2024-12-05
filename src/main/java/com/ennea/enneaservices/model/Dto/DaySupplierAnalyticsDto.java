package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DaySupplierAnalyticsDto {
    Map<String, OrderAnalyticDTO> supplierToOrderAnalytic = new HashMap<>();
}
