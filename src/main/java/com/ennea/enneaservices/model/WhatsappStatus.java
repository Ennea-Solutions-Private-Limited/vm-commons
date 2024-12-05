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
public class WhatsappStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long phoneNumber;
    private String messageId;
    private String template;
    private String status;
    @ManyToOne
    private User user;
    private LocalDateTime creationDateTime = DateTimeUtils.dateTimeInIST().toLocalDateTime();
    private transient boolean isBillable = true;

    public WhatsappStatus(Long phoneNumber, String template, User user) {
        this.phoneNumber = phoneNumber;
        this.messageId = "NA";
        this.template = template;
        this.status = "Queued";
        this.user = user;
    }

    public WhatsappStatus(Long phoneNumber, String template, String status, User user) {
        this.phoneNumber = phoneNumber;
        this.messageId = "NA";
        this.template = template;
        this.status = status;
        this.user = user;
    }

}
