package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WhatsappTemplateResponse {

    private String messaging_product;
    private ArrayList<WhatsappContact> contacts;
    private ArrayList<WhatsappMessage> messages;
    private String error;

}
