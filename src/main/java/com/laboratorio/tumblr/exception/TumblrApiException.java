package com.laboratorio.tumblr.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 01/05/2025
 */
public class TumblrApiException extends RuntimeException {
    protected static final Logger log = LogManager.getLogger(TumblrApiException.class);

    public TumblrApiException(String className, String message, Exception e) {
        super(message);
        this.logException(className, message, e);
    }

    protected void logException(String className, String message, Exception e) {
        log.error("Error en clase {}: {}", className, message);
        log.error("Error: {}", e.getMessage());
        if (e.getCause() != null) {
            log.error("Causa: {}", e.getCause().getMessage());
        }
    }
}