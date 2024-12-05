package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WhatsappWebhookValue {

    private String messaging_product;
    private WhatsappWebhookValueMetadata metadata;
    private List<WhatsappWebhookMessages> messages = new ArrayList<>();
    private List<WhatsappWebhookErrors> errors = new ArrayList<>();
    private List<WhatsappWebhookStatus> statuses = new ArrayList<>();
    private List<WhatsappWebhookContacts> contacts = new ArrayList<>();

}
