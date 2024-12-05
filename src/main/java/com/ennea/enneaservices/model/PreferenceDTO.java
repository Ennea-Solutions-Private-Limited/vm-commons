package com.ennea.enneaservices.model;

import lombok.Data;

import java.util.List;

@Data
public class PreferenceDTO {

    List<PreferenceOption> options;
    private PreferenceFeature feature;
    private Role targetRole;
    private String value;

}
