package com.ennea.enneaservices.model.Dto.erpReplenish;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ERPPurchaseDetailsDTO {

    private Integer purchaseQuantity;

    private Integer purchaseSchemeQuantity;

    private Double purchaseRate;

    private String supplierName;

    private String email;

    private String phoneNumber;

}
