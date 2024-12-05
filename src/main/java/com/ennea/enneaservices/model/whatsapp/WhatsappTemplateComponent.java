package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WhatsappTemplateComponent {

    private Type type;
    private Subtype sub_type;
    private ArrayList<WhatsappTemplateParameter> parameters = new ArrayList<>();
    private Integer index;
    public WhatsappTemplateComponent(Type type, Subtype subtype, Integer index) {
        this.type = type;
        this.sub_type = subtype;
        this.index = index;
    }
    public enum Type {
        header, // Media with Captions
        body, // Text
        button
    }
    public enum Subtype { // Only for Buttons
        quick_reply,
        url
    }

    public enum Index { // Only for Buttons
        start("0"),
        middle("1"),
        end("2");

        public final String value;

        Index(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
