package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

@Data
public class WhatsappTemplateLanguage {

    private String policy = "deterministic";
        // https://developers.facebook.com/docs/whatsapp/cloud-api/reference/messages#template-object
    private String code = "en";
        // https://developers.facebook.com/docs/whatsapp/api/messages/message-templates#supported-languages

}
