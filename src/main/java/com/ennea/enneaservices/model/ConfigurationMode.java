package com.ennea.enneaservices.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ConfigurationMode implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int configuration_id;

    @ManyToOne
    private TransactionMode mode;

    @OneToMany(mappedBy = "configuration_mode_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ConfigurationModeFeature> featurePermissions = new ArrayList<>();

}
