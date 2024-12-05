package com.ennea.enneaservices.model.Dto.axis.apiconnect;

import lombok.Data;

@Data
public class SubHeaderDTO {

    private String requestUUID;

    private String serviceRequestId;

    private String serviceRequestVersion;

    private String channelId;
}
