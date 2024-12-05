package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ERPOrderDTO {

    private long orderId;

    private String partyCode;

    private String partyName;

    private Long phoneNumber;

    private LocalDate orderDate;

    private String comment;

    private boolean representativeOrder;

    private List<ERPOrderProductDTO> products;
}
