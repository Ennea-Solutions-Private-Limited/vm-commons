package com.ennea.enneaservices.model.Dto.Printer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrintResponse {

    private Long printId;

    private String printSummaryHtml;

    private PrintSummary printSummary;
}
