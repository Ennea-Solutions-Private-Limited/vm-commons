package com.ennea.enneaservices.model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepresentativeAttendanceUpdateDTO {
    private Double latitude;
    private Double longitude;
    private String partyCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}
