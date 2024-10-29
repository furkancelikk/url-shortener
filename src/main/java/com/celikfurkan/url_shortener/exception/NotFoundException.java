package com.celikfurkan.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource cannot be found.
 *
 * <p>This exception is marked with a response status of {@link HttpStatus#NOT_FOUND} (404),
 * indicating that the server could not find the requested resource.</p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
