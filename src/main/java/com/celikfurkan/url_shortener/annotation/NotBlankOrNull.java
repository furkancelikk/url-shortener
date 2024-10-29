package com.celikfurkan.url_shortener.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that a field or parameter is not blank or null.
 *
 * <p>This annotation can be applied to fields or method parameters to enforce
 * that the annotated element cannot be empty or contain only whitespace characters.</p>
 */
@Constraint(validatedBy = NotBlankOrNullValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankOrNull {

    /**
     * The name of the field or parameter being validated.
     *
     * @return the name of the field or parameter
     */
    String name();

    /**
     * Indicates whether the annotated field or parameter can be null.
     *
     * @return true if null is allowed, false otherwise
     */
    boolean isNullable() default false;

    /**
     * The error message to be returned when validation fails.
     *
     * @return the validation error message
     */
    String message() default "{name} cannot be empty and cannot contain any whitespace characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
