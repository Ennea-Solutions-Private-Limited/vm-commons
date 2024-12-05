package com.ennea.enneaservices.model.porter.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PorterWebhookPartnerLocation {

    private Double lat;

    @JsonProperty("long")
    private Double lng;

}
