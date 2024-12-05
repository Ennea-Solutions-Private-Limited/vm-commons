package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountStatementRequestDTO {

    private String userId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String email;

}
