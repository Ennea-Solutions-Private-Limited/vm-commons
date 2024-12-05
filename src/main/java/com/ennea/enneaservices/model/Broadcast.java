package com.ennea.enneaservices.model;

import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateParameter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Broadcast implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String partyName;

    private String partyCode;

    @ManyToOne
    private BroadcastTemplate template;

    private String title;

    private LocalDateTime date;

    private Long phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private WhatsappStatus whatsappStatus;

    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    private EmailStatus emailStatus;

    private transient String rowStatus = "Queued";
    private transient List<WhatsappTemplateParameter> templateVariables = new ArrayList<>();
    private transient List<WhatsappTemplateParameter> templateHeaderVariables = new ArrayList<>();
    private transient List<String> ccList = new ArrayList<>();
    private transient String buttonLink = "";
    private transient boolean doNotProcess = false;

    @Transient
    private transient HashMap<String, String> mailVariables = new HashMap<>();

}
