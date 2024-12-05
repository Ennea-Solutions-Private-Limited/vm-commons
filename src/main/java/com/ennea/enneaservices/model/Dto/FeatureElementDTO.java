package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class FeatureElementDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private int id;

    private String key;
    private String type;

}