package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class WhatsappTemplateParameterDocument implements WhatsappTemplateParameter {

    private String type = Type.document.toString();
    private WhatsappTemplateParameterMedia document = new WhatsappTemplateParameterMedia();

    public WhatsappTemplateParameterDocument(String url, String fileName) {
        this.document.setLink(url);
        if(StringUtils.isNotBlank(fileName)){
            this.document.setFilename(fileName);
        }
    }

}
