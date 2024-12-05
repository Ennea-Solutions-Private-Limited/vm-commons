package com.ennea.enneaservices.model.whatsapp.webhook;

import lombok.Data;

@Data
public class WhatsappWebhookErrors {

    private Integer code;
    private String title;
    private String message;
    private ErrorData error_data;
}

@Data
class ErrorData {
    private String details;
}
