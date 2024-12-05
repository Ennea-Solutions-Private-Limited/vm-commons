package com.ennea.enneaservices.model.whatsapp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WhatsappTemplateParameterMedia {

    private String link = null;
    private String filename = null;

}
