package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WhatsappWebhookStatus {

    private String id; // Whatsapp Message ID
    private String recipient_id; // End-user Whatsapp Number
    private String status; // delivered, read, sent
    private String timestamp; // UNIX Timestamp
    private WhatsappWebhookConversation conversation;
    private WhatsappWebhookPricing pricing;
    private List<WhatsappWebhookErrors> errors = new ArrayList<>();

}
