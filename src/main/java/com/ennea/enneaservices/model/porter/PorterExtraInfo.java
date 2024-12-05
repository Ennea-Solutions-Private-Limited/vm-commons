package com.ennea.enneaservices.model.porter;

import lombok.Data;

@Data
public class PorterExtraInfo {

    private Long estPickupTime;
    private Long acceptedTime;
    private Long startedTime;
    private Long endedTime;
    private String partnerName;
    private String partnerNumber;
    private String vehicleNumber;

}
