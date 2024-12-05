package com.ennea.enneaservices.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortagePDFResponse {

    private String filename;
    private String link;

}
