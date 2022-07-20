package com.example.BankApplication.auth.registration.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MinForDateValidator implements ConstraintValidator<MinForDateConstraint, LocalDate> {

    @Override
    public void initialize(MinForDateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate localDate1 = LocalDate.now().minusYears(18);
        return localDate.isBefore(localDate1);
    }
}
