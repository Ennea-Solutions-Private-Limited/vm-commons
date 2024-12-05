package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

import java.util.List;

@Data
public class WhatsappWebhookNotificationPayload {

    private String object;
    private List<WhatsappWebhookEntry> entry;

}
