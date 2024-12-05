package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

@Data
public class CSquareOutstanding {

    private String customerCode;

    private String invPrefix;

    private String invoiceNumber;

    private String invDate;

    private String dueDate;

    private String invAmt;

    private Double balAmt;

    private String days;
}
