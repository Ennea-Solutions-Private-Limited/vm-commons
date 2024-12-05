package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
public class SupplierRepresentativeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private UserMetadataDTO supplier;

    private UserProfileDTO representative;

    private Set<String> salesmanCodes;

    private RequestStatusDTO requestStatus;

    private boolean isMaster;

    private boolean enableOffline;
}
