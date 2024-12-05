package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPProductDTO {

    //productCode in inventory Availability
    @NotBlank
    private String id;

    //productName
    @NotBlank
    private String name;

    //other alias need to save new
    private String code;

    private String manufacturer;
    // concat manufacturer and division to division in IA
    private String division;

    private String packing;

    private String casePacking;

    private List<ERPBatchDTO> batches = new ArrayList<>();
}
