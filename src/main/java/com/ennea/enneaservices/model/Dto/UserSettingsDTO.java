package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.Preference;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserSettingsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String businessName;

    private String displayName;

    private Long phoneNumber;

    // metadata info for supplier -- computed not present in db directly
    private String minOrderValue;

    private RoleDTO role;

    private SettingsDTO settings;

    private List<Preference> preferences;

}
