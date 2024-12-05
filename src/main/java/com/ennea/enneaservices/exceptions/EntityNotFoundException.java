package com.ennea.enneaservices.exceptions;

import com.ennea.enneaservices.model.Dto.ApiError;

import java.io.Serial;

public class EntityNotFoundException extends CustomEnneaExeption {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
        super();
    }

    /**
     * Constructs a EntityNotFoundException using the given message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a EntityNotFoundException using the given message and underlying
     * cause.
     *
     * @param cause The underlying cause.
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a EntityNotFoundException using the given message and underlying
     * cause.
     *
     * @param message The message explaining the reason for the exception.
     * @param cause   The underlying cause.
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a EntityNotFoundException using the given message and underlying
     * cause.
     *
     * @param errorResponse The ApiError containing detailed error message with code.
     */
    public EntityNotFoundException(ApiError errorResponse) {
        super(errorResponse);
    }

}
