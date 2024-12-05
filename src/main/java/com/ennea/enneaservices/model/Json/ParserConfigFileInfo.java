package com.ennea.enneaservices.model.Json;

import lombok.Data;

@Data
public class ParserConfigFileInfo {

    private Integer headerRowNumber = 1;

    private String dateFormat = "dd-MM-yyyy";

}
