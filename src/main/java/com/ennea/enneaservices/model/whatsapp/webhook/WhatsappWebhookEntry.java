package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WhatsappWebhookEntry {

    private String id;
    private List<WhatsappWebhookChange> changes = new ArrayList<>();

}
