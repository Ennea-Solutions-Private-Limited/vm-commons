package com.ennea.enneaservices.model.Dto.oldDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class OrderTypeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private TransactionTypeDTO transactionType;
    private TransactionSubTypeDTO transactionSubType;

    public OrderTypeDTO() {
        this.id = 1;
        this.transactionType = new TransactionTypeDTO(1, "Sale");
        this.transactionSubType = new TransactionSubTypeDTO(1, "Normal");
    }

    @Data
    @AllArgsConstructor
    public static class TransactionTypeDTO {
        private int id;
        private String type;
    }

    @Data
    @AllArgsConstructor
    public static class TransactionSubTypeDTO {
        private int id;
        private String type;
    }
}