package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPOrderPrintDTO {

    private Long printId;

    private String printSummaryHtml;
}
