package com.ennea.enneaservices.model.porter.webhook;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PorterWebhook {

    private String status;

    private String orderId;

    private PorterWebhookOrderDetails orderDetails;

}
