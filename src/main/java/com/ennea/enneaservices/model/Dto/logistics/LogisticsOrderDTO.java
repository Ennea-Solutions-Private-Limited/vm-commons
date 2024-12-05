package com.ennea.enneaservices.model.Dto.logistics;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogisticsOrderDTO {

    private Long gridId;

    private String userName;

    private String provider;

    private Long estimatedPrice;

    private Long finalPrice;

    private String status;

    private String trackingLink;

    private LocalDateTime orderCreationDatetime;

    private String extraInfo;

    private LogisticsOrderControlsDTO controls;

}
