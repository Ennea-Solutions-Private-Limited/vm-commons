package com.ennea.enneaservices.notification;

import com.ennea.enneaservices.model.BroadcastLimit;
import com.ennea.enneaservices.model.EmailStatus;
import com.ennea.enneaservices.model.WhatsappStatus;
import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateMessage;
import com.ennea.enneaservices.notification.email.BaseEmailTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class NotificationPayload {

    private BroadcastLimit userLimit;
    private HashMap<BaseEmailTemplate, EmailStatus> emails;
    private HashMap<WhatsappTemplateMessage, WhatsappStatus> messages;

}
