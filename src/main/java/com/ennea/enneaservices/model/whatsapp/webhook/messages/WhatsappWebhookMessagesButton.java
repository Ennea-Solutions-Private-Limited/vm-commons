package com.ennea.enneaservices.model.whatsapp.webhook.messages;

import lombok.Data;

@Data
public class WhatsappWebhookMessagesButton {

    private String payload;
    private String text;

}
