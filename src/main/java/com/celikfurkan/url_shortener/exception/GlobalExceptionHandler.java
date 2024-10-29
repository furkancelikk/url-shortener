package com.celikfurkan.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Global exception handler for the application.
 *
 * <p>This class handles exceptions thrown by the application and returns
 * appropriate HTTP responses. It centralizes the error handling logic
 * for various exceptions that can occur during request processing.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link MethodArgumentNotValidException} when validation on an
     * argument annotated with @Valid fails.
     *
     * @param exception the validation exception
     * @return a ResponseEntity containing the validation errors and a 400 Bad Request status
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        errorResponse.put("error", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link NotFoundException} when a requested resource cannot be found.
     *
     * @param exception the not found exception
     * @return a ResponseEntity containing the error message and a 404 Not Found status
     */
    @ExceptionHandler(value = NotFoundException.class)
    private ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link AlreadyExistsException} when a resource that is being created already exists.
     *
     * @param exception the already exists exception
     * @return a ResponseEntity containing the error message and a 409 Conflict status
     */
    @ExceptionHandler(value = AlreadyExistsException.class)
    private ResponseEntity<?> handleNotFoundException(AlreadyExistsException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    /**
     * Handles {@link NoResourceFoundException} when a resource cannot be found.
     *
     * @param exception the no resource found exception
     * @return a ResponseEntity containing the error message and the appropriate status code
     */
    @ExceptionHandler(value = NoResourceFoundException.class)
    private ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getResourcePath() + " not found");
        return new ResponseEntity<>(errors, exception.getStatusCode());
    }

    /**
     * Handles {@link HandlerMethodValidationException} when validation fails on a method parameter.
     *
     * @param exception the handler method validation exception
     * @return a ResponseEntity containing the validation error message and a 400 Bad Request status
     */
    @ExceptionHandler(value = HandlerMethodValidationException.class)
    private ResponseEntity<?> handleMethodValidationException(HandlerMethodValidationException exception) {
        Map<String, String> errors = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        if (!CollectionUtils.isEmpty(exception.getAllErrors())) {
            builder.append(exception.getAllErrors().get(0).getDefaultMessage());
        }
        errors.put("error", builder.toString());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles general exceptions that are not caught by other handlers.
     *
     * @param exception the general exception
     * @return a ResponseEntity containing a generic error message and a 500 Internal Server Error status
     */
    @ExceptionHandler(value = Exception.class)
    private ResponseEntity<?> handleException(Exception exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Internal error");
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
