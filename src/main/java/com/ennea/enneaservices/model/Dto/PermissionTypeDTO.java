package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PermissionTypeDTO implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private int id;

    private String name;

    @JsonInclude(Include.NON_EMPTY)
    private List<OrderTransitionDTO> orderTransitions = new ArrayList<>();

    @JsonInclude(Include.NON_EMPTY)
    private List<FeatureElementDTO> elements = new ArrayList<>();

    @JsonInclude(Include.NON_EMPTY)
    private List<PlaceOrderTypePermissionDTO> placeOrderTypePermissions = new ArrayList<>();
}