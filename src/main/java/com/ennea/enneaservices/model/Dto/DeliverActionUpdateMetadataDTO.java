package com.ennea.enneaservices.model.Dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliverActionUpdateMetadataDTO extends ActionUpdateMetadataDTO {

    private int noOfBoxes;
}
