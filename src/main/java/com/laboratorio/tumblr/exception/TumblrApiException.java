package com.laboratorio.tumblr.exception;

/**
 *
 * author Rafael
 * version 1.2
 * created 01/05/2025
 * updated 24/12/2025
 */
public class TumblrApiException extends RuntimeException {
    private final Throwable causaOriginal;

    public TumblrApiException(String message) {
        super(message);
        this.causaOriginal = null;
    }

    public TumblrApiException(String message, Throwable causaOriginal) {
        super(message, causaOriginal);
        this.causaOriginal = causaOriginal;
    }

    @Override
    public String getMessage() {
        if (this.causaOriginal != null) {
            return super.getMessage() + " | Causa original: " + this.causaOriginal.getMessage();
        }

        return super.getMessage();
    }
}