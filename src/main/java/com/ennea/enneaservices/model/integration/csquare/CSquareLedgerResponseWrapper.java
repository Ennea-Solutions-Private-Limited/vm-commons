package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CSquareLedgerResponseWrapper extends CSquareResponseWrapper {
    private CSquareCustomerResult result;
}
