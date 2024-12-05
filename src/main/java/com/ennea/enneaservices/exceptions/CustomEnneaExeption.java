package com.ennea.enneaservices.exceptions;

import com.ennea.enneaservices.model.Dto.ApiError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Setter
@Getter
public class CustomEnneaExeption extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1488638787124982982L;

    private ApiError errorResponse;

    public CustomEnneaExeption() {
        super();
    }

    /**
     * Constructs a EnneaException using the given message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public CustomEnneaExeption(String message) {
        super(message);
    }

    /**
     * Constructs a EnneaException using the given message and underlying cause.
     *
     * @param cause The underlying cause.
     */
    public CustomEnneaExeption(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a EnneaException using the given message and underlying cause.
     *
     * @param message The message explaining the reason for the exception.
     * @param cause   The underlying cause.
     */
    public CustomEnneaExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomEnneaExeption(ApiError errorResponse) {
        this.errorResponse = errorResponse;
    }

    public CustomEnneaExeption(String message, HttpStatus status) {
        super(message);
    }

}
