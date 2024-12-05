package com.ennea.enneaservices.model.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseContainer {

    private Object response = "";

    private HttpStatus status = HttpStatus.BAD_REQUEST;

}
