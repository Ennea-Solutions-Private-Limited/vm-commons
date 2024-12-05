package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

@Data
public class WhatsappWebhookConversation {

    private String id;
    private String expiration_timestamp;
    private WhatsappWebhookConversationOrigin origin;

}

@Data
class WhatsappWebhookConversationOrigin {

    private String type;

}
