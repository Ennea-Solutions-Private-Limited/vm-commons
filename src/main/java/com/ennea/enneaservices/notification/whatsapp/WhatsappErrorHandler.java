package com.ennea.enneaservices.notification.whatsapp;

import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.whatsapp.WhatsappClientError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class WhatsappErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String statusText = response.getStatusText();
        byte[] body = getResponseBody(response);
        String bodyString = new String(body, StandardCharsets.UTF_8);
        WhatsappClientError err;
        try {
            err = new ObjectMapper().readValue(bodyString, WhatsappClientError.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse Whatsapp client error: {}", e.getMessage());
            throw new CustomEnneaExeption("Unable to process Whatsapp client error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        err.setStatusCode(String.valueOf(response.getStatusCode().value()));
        err.setStatusText(statusText);

        throw new IOException(err.toString());
    }

}
