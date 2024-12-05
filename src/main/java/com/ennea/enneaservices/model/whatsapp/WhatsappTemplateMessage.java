package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WhatsappTemplateMessage {

    private String messaging_product = "whatsapp";
    private String recipient_type = "individual";
    private String to = ""; // recipient phone number or Whatsapp ID
    private String type = "template";
    private WhatsappTemplate template = new WhatsappTemplate();

    public WhatsappTemplateMessage(String to, String templateName) {
        this.to = to;
        this.template.setName(templateName);
    }

}
