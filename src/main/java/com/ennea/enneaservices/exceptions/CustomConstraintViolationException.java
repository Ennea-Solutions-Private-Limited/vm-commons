package com.ennea.enneaservices.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.io.Serial;
import java.util.Set;

public class CustomConstraintViolationException extends ConstraintViolationException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);

    }

}
