package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class TotalAnalyticsDTO {
    HashMap<String, DaySupplierAnalyticsDto> dayToSupplierAnalytics = new HashMap<>();
}
