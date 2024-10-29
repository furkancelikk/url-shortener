package com.celikfurkan.url_shortener.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator class for the {@link NotBlankOrNull} annotation.
 *
 * <p>This class implements the validation logic to ensure that a given string
 * is neither blank nor null, based on the configuration specified in the annotation.</p>
 */
public class NotBlankOrNullValidator implements ConstraintValidator<NotBlankOrNull, String> {
    private boolean isNullable;

    /**
     * Initializes the validator with the parameters specified in the {@link NotBlankOrNull} annotation.
     *
     * @param constraintAnnotation the annotation instance for the constraint
     */
    @Override
    public void initialize(NotBlankOrNull constraintAnnotation) {
        isNullable = constraintAnnotation.isNullable();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Validates the given string value.
     *
     * @param value   the string value to validate
     * @param context the context in which the constraint is evaluated
     * @return true if the value is valid (not null or blank), false otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return isNullable;
        }

        if (value.isBlank()) {
            return false;
        }

        for (int i = 0; i < value.length(); i++) {
            char charAt = value.charAt(i);
            if (Character.isWhitespace(charAt)) {
                return false;
            }
        }
        return true;
    }
}
