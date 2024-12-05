package com.ennea.enneaservices.model.whatsapp.webhook.messages;

import lombok.Data;

@Data
public class WhatsappWebhookMessagesContext {

    private boolean forwarded;
    private boolean frequently_forwarded;
    private String from;
    private String id;

}
