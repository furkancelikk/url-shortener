package com.celikfurkan.url_shortener.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankOrNullValidator implements ConstraintValidator<NotBlankOrNull, String> {
    private boolean isNullable;

    @Override
    public void initialize(NotBlankOrNull constraintAnnotation) {
        isNullable = constraintAnnotation.isNullable();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

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
