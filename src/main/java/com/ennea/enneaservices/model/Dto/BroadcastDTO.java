package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BroadcastDTO {

    private Long id;
    private String partyName;
    private String partyCode;
    private String templateName;
    private String title;
    private LocalDateTime date;
    private Long phoneNumber;
    private String whatsappStatus;
    private String emailId;
    private String emailStatus;

}
