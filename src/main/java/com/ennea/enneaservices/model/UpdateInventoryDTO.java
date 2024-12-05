package com.ennea.enneaservices.model;

import com.ennea.enneaservices.model.Dto.NewInventoryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInventoryDTO extends NewInventoryDTO implements Serializable {
    private Long id;

    private boolean deleted;
}
