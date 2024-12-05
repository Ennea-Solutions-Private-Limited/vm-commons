package com.ennea.enneaservices.model;

import com.ennea.enneaservices.utils.DateTimeUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class EmailStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailId;
    private String messageId;
    private String template;
    private String status;
    @ManyToOne
    private User user;
    private LocalDateTime creationDateTime = DateTimeUtils.dateTimeInIST().toLocalDateTime();

    public EmailStatus(String emailId, String template, User user) {
        this.emailId = emailId;
        this.messageId = "NA";
        this.template = template;
        this.status = "Queued";
        this.user = user;
    }

}
