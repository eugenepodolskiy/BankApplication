package com.example.BankApplication.auth.registration.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = MinForDateValidator.class)
@Documented
public @interface MinForDateConstraint {
    String message() default "You are younger than 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
