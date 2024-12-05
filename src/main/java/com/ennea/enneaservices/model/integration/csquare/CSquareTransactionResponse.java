package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CSquareTransactionResponse extends CSquareResponseWrapper {

    private CSquareTransactionResult result;

}
