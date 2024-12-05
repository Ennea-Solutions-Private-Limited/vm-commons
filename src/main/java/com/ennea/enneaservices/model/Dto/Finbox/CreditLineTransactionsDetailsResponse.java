package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

import java.util.List;

@Data
public class CreditLineTransactionsDetailsResponse {

    private List<CreditLineTransactionDetails> transactions;
}
