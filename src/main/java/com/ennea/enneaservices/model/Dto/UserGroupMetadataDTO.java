package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserGroupMetadataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private UserMetadataDTO owner;

}
