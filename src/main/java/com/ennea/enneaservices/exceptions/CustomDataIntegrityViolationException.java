package com.ennea.enneaservices.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;
import java.util.Map;

public class CustomDataIntegrityViolationException extends DataIntegrityViolationException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomDataIntegrityViolationException(String msg) {

        super(msg);
    }

    public CustomDataIntegrityViolationException(String msg, Map<String, String> constraintMap) {

        super(msg);

    }

}
