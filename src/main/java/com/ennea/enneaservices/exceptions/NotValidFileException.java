package com.ennea.enneaservices.exceptions;

import com.ennea.enneaservices.model.Dto.ApiError;

import java.io.Serial;

public class NotValidFileException extends CustomEnneaExeption {


    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public NotValidFileException() {
        super();
    }

    /**
     * Constructs a NotValidFileException using the given message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public NotValidFileException(String message) {
        super(message);
    }

    /**
     * Constructs a NotValidFileException using the given message and underlying
     * cause.
     *
     * @param cause The underlying cause.
     */
    public NotValidFileException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a NotValidFileException using the given message and underlying
     * cause.
     *
     * @param message The message explaining the reason for the exception.
     * @param cause   The underlying cause.
     */
    public NotValidFileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a NotValidFileException using the given message and underlying
     * cause.
     *
     * @param errorResponse The ApiError containing detailed error message with code.
     */
    public NotValidFileException(ApiError errorResponse) {
        super(errorResponse);
    }
}
