package com.ennea.enneaservices.model.Dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepresentativeAttendanceDTO {

    private LocalDateTime date;

    private String partyCode;

    @ManyToOne
    private UserMetadataDTO representative;
}
