package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

@Data
public class WhatsappTemplateParameterText implements WhatsappTemplateParameter {

    private String type = Type.text.toString();
    private String text;

    public WhatsappTemplateParameterText(String text) {
        this.text = text;
    }

}
