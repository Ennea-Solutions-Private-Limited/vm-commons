package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FeatureDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private int id;

    private String name;

    //Remove code and seq
    private String code;

    private int seq;

    private List<PermissionTypeDTO> permissionTypes = new ArrayList<>();

}