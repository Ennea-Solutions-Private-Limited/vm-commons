package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMetadataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private String businessName;

    private String contactPerson;

    private Long phoneNumber;

    private String roleName;

}
