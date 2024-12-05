package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

@Data
public class WhatsappWebhookContacts {

    private String wa_id;
    private Profile profile;

}

@Data
class Profile {
    private String name;
}
