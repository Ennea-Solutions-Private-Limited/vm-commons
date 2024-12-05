package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDeviceDetailsDTO {
    private String buildNumber;

    private String version;

    private String deviceDetails;

    private LocalDateTime lastUpdatedDateTime;
}
