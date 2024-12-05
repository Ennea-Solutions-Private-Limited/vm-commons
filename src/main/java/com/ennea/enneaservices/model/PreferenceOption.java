package com.ennea.enneaservices.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class PreferenceOption implements Serializable {

    private String value;

}
