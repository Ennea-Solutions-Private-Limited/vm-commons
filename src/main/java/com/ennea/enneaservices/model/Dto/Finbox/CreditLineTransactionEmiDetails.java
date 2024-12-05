package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

import java.util.List;

@Data
public class CreditLineTransactionEmiDetails {

    private Double amount;
    private Integer installmentNum;
    private List<EMICharges> charges;
    private String status;
    private String dueDate;
    private Double amountReceived;
    private String paidDate;
    private Double totalPayable;

}

@Data
class EMICharges {

    private String chargeType;
    private Double charge;

}