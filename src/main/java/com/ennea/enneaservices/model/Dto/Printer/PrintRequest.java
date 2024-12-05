package com.ennea.enneaservices.model.Dto.Printer;

import lombok.Getter;

import java.util.List;

@Getter
public class PrintRequest {

    private String businessName;

    private String uuid;

    private String key;

    private String version;

    private List<Long> printIds;
}
