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

@RestControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(value = NotFoundException.class)
    private ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    private ResponseEntity<?> handleNotFoundException(AlreadyExistsException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    private ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getResourcePath() + " not found");
        return new ResponseEntity<>(errors, exception.getStatusCode());
    }

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

    @ExceptionHandler(value = Exception.class)
    private ResponseEntity<?> handleException(Exception exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Internal error");
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
