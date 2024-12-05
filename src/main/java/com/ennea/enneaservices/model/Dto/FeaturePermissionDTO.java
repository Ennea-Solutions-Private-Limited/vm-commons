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
public class FeaturePermissionDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private PermissionTypeDTO permissionType;

    private FeatureDTO feature;

    @JsonIgnore
    private List<RoleModeFeatureDTO> roleModes = new ArrayList<>();

    @JsonIgnore
    private List<PlaceOrderTypePermissionDTO> placeOrderTypePermissions = new ArrayList<>();
}
