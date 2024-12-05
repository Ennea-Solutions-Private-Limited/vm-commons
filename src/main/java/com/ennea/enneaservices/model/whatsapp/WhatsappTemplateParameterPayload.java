package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

@Data
public class WhatsappTemplateParameterPayload implements WhatsappTemplateParameter {

    private String type = Type.payload.toString();
    private String payload;

    public WhatsappTemplateParameterPayload(String url) {
        this.payload = url;
    }

}
