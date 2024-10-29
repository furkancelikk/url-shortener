package com.celikfurkan.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an attempt is made to create a resource that already exists.
 *
 * <p>This exception is marked with a response status of {@link HttpStatus#CONFLICT} (409),
 * indicating that the request could not be completed due to a conflict with the current state
 * of the target resource.</p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
