package com.ennea.enneaservices.model.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleModeDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private List<RoleModeFeatureDTO> featurePermissions = new ArrayList<>();

}
