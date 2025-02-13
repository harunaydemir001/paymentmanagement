package com.harun.entity.annotation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[.,@#$%^&*!].*");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile(".*[\\s].*");

    private String minLengthMessage;
    private String uppercaseMessage;
    private String lowercaseMessage;
    private String digitMessage;
    private String specialCharMessage;
    private String whitespaceMessage;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.minLengthMessage = constraintAnnotation.minLengthMessage();
        this.uppercaseMessage = constraintAnnotation.uppercaseMessage();
        this.lowercaseMessage = constraintAnnotation.lowercaseMessage();
        this.digitMessage = constraintAnnotation.digitMessage();
        this.specialCharMessage = constraintAnnotation.specialCharMessage();
        this.whitespaceMessage = constraintAnnotation.whitespaceMessage();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if (password.length() < 8) {
            context.buildConstraintViolationWithTemplate(minLengthMessage)
                    .addConstraintViolation();
            isValid = false;
        }
        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate(uppercaseMessage)
                    .addConstraintViolation();
            isValid = false;
        }
        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate(lowercaseMessage)
                    .addConstraintViolation();
            isValid = false;
        }
        if (!DIGIT_PATTERN.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate(digitMessage)
                    .addConstraintViolation();
            isValid = false;
        }
        if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate(specialCharMessage)
                    .addConstraintViolation();
            isValid = false;
        }
        if (WHITESPACE_PATTERN.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate(whitespaceMessage)
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}