package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

@Data
public class WhatsappTemplateParameterImage implements WhatsappTemplateParameter {

    private String type = Type.image.toString();
    private WhatsappTemplateParameterMedia image = new WhatsappTemplateParameterMedia();

    public WhatsappTemplateParameterImage(String url) {
        this.image.setLink(url);
    }

}
