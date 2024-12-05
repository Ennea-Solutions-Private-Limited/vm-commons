package com.ennea.enneaservices.model.whatsapp.webhook;

import com.ennea.enneaservices.model.whatsapp.webhook.messages.WhatsappWebhookMessagesAttachment;
import com.ennea.enneaservices.model.whatsapp.webhook.messages.WhatsappWebhookMessagesButton;
import com.ennea.enneaservices.model.whatsapp.webhook.messages.WhatsappWebhookMessagesContext;
import com.ennea.enneaservices.model.whatsapp.webhook.messages.WhatsappWebhookMessagesText;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WhatsappWebhookMessages {

    private WhatsappWebhookMessagesAttachment audio;
    private WhatsappWebhookMessagesButton button;
    private WhatsappWebhookMessagesContext context;
    private WhatsappWebhookMessagesAttachment document;
    private List<WhatsappWebhookErrors> errors = new ArrayList<>();
    private String from;
    private String id;
    private WhatsappWebhookMessagesAttachment image;
    private WhatsappWebhookMessagesText text;
    private String timestamp;
    private String type;
    private WhatsappWebhookMessagesAttachment video;

}