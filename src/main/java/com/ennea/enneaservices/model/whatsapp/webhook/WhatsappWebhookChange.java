package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

@Data
public class WhatsappWebhookChange {

    private WhatsappWebhookValue value;
    private String field;

}
