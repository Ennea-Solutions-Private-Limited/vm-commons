package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String role;

    @JsonInclude(Include.NON_EMPTY)
    private List<TransactionModeDTO> transactionModes = new ArrayList<>();

}
