package com.laboratorio.tumblr.exception;

/**
 *
 * author Rafael
 * version 1.1
 * created 01/05/2025
 * updated 02/05/2025
 */
public class TumblrApiException extends RuntimeException {
    private Throwable causaOriginal = null;

    public TumblrApiException(String message) {
        super(message);
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