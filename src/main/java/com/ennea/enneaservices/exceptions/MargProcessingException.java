package com.ennea.enneaservices.exceptions;

import com.ennea.enneaservices.model.Dto.ApiError;

import java.io.Serial;

public class MargProcessingException extends CustomEnneaExeption {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public MargProcessingException() {
        super();
    }

    /**
     * Constructs a MargProcessingException using the given message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public MargProcessingException(String message) {
        super(message);
    }

    /**
     * Constructs a MargProcessingException using the given message and underlying
     * cause.
     *
     * @param cause The underlying cause.
     */
    public MargProcessingException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a MargProcessingException using the given message and underlying
     * cause.
     *
     * @param message The message explaining the reason for the exception.
     * @param cause   The underlying cause.
     */
    public MargProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a MargProcessingException using the given message and underlying
     * cause.
     *
     * @param errorResponse The ApiError containing detailed error message with code.
     */
    public MargProcessingException(ApiError errorResponse) {
        super(errorResponse);
    }

}
