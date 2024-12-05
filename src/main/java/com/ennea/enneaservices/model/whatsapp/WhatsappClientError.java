package com.ennea.enneaservices.model.whatsapp;

import lombok.Data;

@Data
public class WhatsappClientError {

    private ErrorDetails error;
    private String statusCode;
    private String statusText;

    @Data
    private static class ErrorDetails {
        private final String message;
        private final String type;
        private final String code;
        private final String fbtrace_id;
        private final ErrorData error_data;
    }

    @Data
    private static class ErrorData {
        private final String messaging_product;
        private final String details;
    }
}
