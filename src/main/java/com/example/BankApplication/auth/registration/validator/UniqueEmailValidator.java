package com.example.BankApplication.auth.registration.validator;


import com.example.BankApplication.auth.appuser.AppUser;
import com.example.BankApplication.auth.appuser.AppUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Optional;

@Configurable
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint,String>{


    private final AppUserService appUserService;

    @Autowired
    public UniqueEmailValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email==null){
            return true;
        }
        return !appUserService.isEmailRegistered(email);
    }

}
