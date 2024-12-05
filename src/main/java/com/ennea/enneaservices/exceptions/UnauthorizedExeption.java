package com.ennea.enneaservices.exceptions;

import com.ennea.enneaservices.model.Dto.ApiError;

import java.io.Serial;

public class UnauthorizedExeption extends CustomEnneaExeption {


    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public UnauthorizedExeption() {
        super();
    }

    /**
     * Constructs a UnauthorizedExeption using the given message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public UnauthorizedExeption(String message) {
        super(message);
    }

    /**
     * Constructs a UnauthorizedExeption using the given message and underlying
     * cause.
     *
     * @param cause The underlying cause.
     */
    public UnauthorizedExeption(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a UnauthorizedExeption using the given message and underlying
     * cause.
     *
     * @param message The message explaining the reason for the exception.
     * @param cause   The underlying cause.
     */
    public UnauthorizedExeption(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a UnauthorizedExeption using the given message and underlying
     * cause.
     *
     * @param errorResponse The ApiError containing detailed error message with code.
     */
    public UnauthorizedExeption(ApiError errorResponse) {
        super(errorResponse);
    }
}
