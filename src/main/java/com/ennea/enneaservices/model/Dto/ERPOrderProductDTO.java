package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPOrderProductDTO {

    private String productCode;

    private String productName;

    private String packing;

    private float quantity;

    private float free;

    private String offer;

    private double rate;

    private String comment;
}
