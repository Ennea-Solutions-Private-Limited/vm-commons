package com.ennea.enneaservices.model.porter.webhook;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PorterWebhookOrderDetails {

    private Long eventTs;

    private PorterWebhookPartnerLocation partnerLocation;

    private PorterWebhookDriverDetails driverDetails;

    private Long estimatedTripFare;

    private Long actualTripFare;

}
