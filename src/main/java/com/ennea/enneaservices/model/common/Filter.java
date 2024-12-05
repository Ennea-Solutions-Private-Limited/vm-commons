package com.ennea.enneaservices.model.common;

import lombok.Data;

import java.util.List;

@Data
public class Filter {

    private String field;
    private List<String> selections;

}
