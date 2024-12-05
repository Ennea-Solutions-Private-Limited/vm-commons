package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

@Data
public class CreateMutoothCreditTransactionDTO {
    private double withdrawalAmount;
    private String transactionID;
    private String merchantName;
    private String merchantBankAccountNumber;
    private String merchantIfscCode;
    private String merchantGstNumber;
    private String sourceEntityID;
    private String customerID;
}
