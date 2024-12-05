package com.ennea.enneaservices.enums;

import lombok.Getter;

@Getter
public enum BannerEnum {

    FULL_PAGE("Full page"),

    LEVEL_ONE("One"),

    LEVEL_TWO("Two"),

    LEVEL_THREE("Three");


    private final String type;

    BannerEnum(String type) {
        this.type = type;
    }
}
