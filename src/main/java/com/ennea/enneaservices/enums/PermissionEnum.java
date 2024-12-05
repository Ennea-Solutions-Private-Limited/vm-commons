package com.ennea.enneaservices.enums;

public enum PermissionEnum {
    GET("Read"),
    POST("Create"),
    PUT("Update"),
    DELETE("Delete");
    private final String type;

    PermissionEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
