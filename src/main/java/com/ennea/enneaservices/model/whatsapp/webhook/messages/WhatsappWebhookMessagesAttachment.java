package com.ennea.enneaservices.model.whatsapp.webhook.messages;

import lombok.Data;

@Data
public class WhatsappWebhookMessagesAttachment {

    private String caption;
    private String filename;
    private String sha256;
    private String mime_type;
    private String id;

}
