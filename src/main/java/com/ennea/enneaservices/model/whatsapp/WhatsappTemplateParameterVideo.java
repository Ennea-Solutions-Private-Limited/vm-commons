package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

@Data
public class WhatsappTemplateParameterVideo implements WhatsappTemplateParameter {

    private String type = Type.video.toString();
    private WhatsappTemplateParameterMedia video = new WhatsappTemplateParameterMedia();

    public WhatsappTemplateParameterVideo(String url) {
        this.video.setLink(url);
    }

}
