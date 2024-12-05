package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WhatsappTemplate {

    private String name = "";
    private WhatsappTemplateLanguage language = new WhatsappTemplateLanguage();
    private ArrayList<WhatsappTemplateComponent> components = new ArrayList<>();


}
