package com.harun.common.annotation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String minLengthMessage() default "Password must be at least 8 characters long.";

    String uppercaseMessage() default "Password must contain at least one uppercase letter.";

    String lowercaseMessage() default "Password must contain at least one lowercase letter.";

    String digitMessage() default "Password must contain at least one digit";

    String specialCharMessage() default "Password must contain at least one special character.";

    String whitespaceMessage() default "Password must not contain spaces or tabs.";
}