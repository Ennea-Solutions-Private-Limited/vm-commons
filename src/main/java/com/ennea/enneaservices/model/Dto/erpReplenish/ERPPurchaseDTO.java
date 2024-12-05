package com.ennea.enneaservices.model.Dto.erpReplenish;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ERPPurchaseDTO {

    private String productCode;

    private String productName;

    private String pack;

    private Integer stock;

    private Integer qtyPerBox;

    private Integer qtyPerCase;

    private Integer salesQty;

    private Integer purchaseQty;

    private String companyName;

    private List<ERPPurchaseDetailsDTO> details = new ArrayList<>();

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

}
