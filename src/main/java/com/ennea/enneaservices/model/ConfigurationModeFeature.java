package com.ennea.enneaservices.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ConfigurationModeFeature implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int configuration_mode_id;

    @ManyToOne
    private FeaturePermission featurePermission;

    @OneToMany(mappedBy = "configuration_mode_feature_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ConfigurationModeFeatureElement> roleModefeatureElements = new ArrayList<>();

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "configuration_mode_feature_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PlaceOrderTypePermission> placeOrderTypePermissions = new ArrayList<>();

}
