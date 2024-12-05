package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuotationOrderDTO implements Serializable {

    private Long quotationId;

    private int quantity;
}
