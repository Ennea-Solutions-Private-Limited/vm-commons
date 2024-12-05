package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class SupplierCustomerRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long supplierId;

    private Set<String> customerDivisions = new HashSet<>();

}
