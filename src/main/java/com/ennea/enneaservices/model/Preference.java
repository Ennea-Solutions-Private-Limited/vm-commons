package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Preference implements Serializable {

    @ManyToOne
    private PreferenceDefault preferenceDefault;
    private String value;
    private Boolean isActive;

    public Preference(PreferenceDefault pref, String value) {
        setPreferenceDefault(pref);
        setValue(value);
        setIsActive(Boolean.TRUE);
    }
}
