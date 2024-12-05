package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Json.ParserConfigFileInfo;
import lombok.Data;

import java.util.HashMap;

@Data
public class ParserConfigDTO {

    private String template;

    private ParserConfigFileInfo fileInfo;

    private HashMap<String, String> headerMap;

}
