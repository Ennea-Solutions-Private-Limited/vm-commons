package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionDTO {

    private Long id;

    private UserMetadataDTO user;

    private LocalDate dueDate;

    private String title;

    private String status;

    private double renewalCost;
}
