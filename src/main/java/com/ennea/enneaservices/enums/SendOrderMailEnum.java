package com.ennea.enneaservices.enums;

public enum SendOrderMailEnum {
    PRINT,
    THUNDERBIRD,
    MAIL,
    DEFAULT;

    public static SendOrderMailEnum fromString(String value) {
        if(value != null){
            try {
                return SendOrderMailEnum.valueOf(value.toUpperCase());
            } catch (IllegalArgumentException e) {
                return DEFAULT;
            }
        }
        return DEFAULT;
    }
}
