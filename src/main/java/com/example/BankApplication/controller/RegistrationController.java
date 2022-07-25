package com.example.BankApplication.controller;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.auth.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;


    @GetMapping("/confirm")
    public String confirmAppUser(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @GetMapping
    public String registrationForm(AppUser appUser,Model model){
        model.addAttribute("appUser", appUser);
        return "registration_form";
    }

    @PostMapping
    public String registerAppUser(@RequestBody @Validated @ModelAttribute("appUser") AppUser appUser, BindingResult bindingResult, Model model){
        if(appUser.getPassword()!=null && appUser.getConfirmPassword()!=null){
            if(!appUser.getConfirmPassword().equals(appUser.getPassword())){
                bindingResult.addError(new FieldError("appUser","confirmPassword","Password doesn't match"));
            }
        }
        if(appUser.getDateOfBirth()!=null){
            if(!appUser.getDateOfBirth().isBefore(LocalDate.now().minusYears(18))){
                bindingResult.addError(new FieldError("appUser","dateOfBirth","You are younger than 18"));
            }
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            System.out.println(appUser.getCreationDate());
            return "registration_form";
        }
        registrationService.registerNewAppUser(appUser);
            return "redirect:/login";
    }

}
