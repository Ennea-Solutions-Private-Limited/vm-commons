package com.ennea.enneaservices.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class PreferenceDefault implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role sourceRole;

    @ManyToOne
    private PreferenceFeature feature;

    @ManyToOne
    private Role targetRole;

    private String value;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "preference_option", joinColumns = {@JoinColumn(name = "preference_default_id")})
    private List<PreferenceOption> options;

}
