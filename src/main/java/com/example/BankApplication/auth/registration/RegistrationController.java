package com.example.BankApplication.auth.registration;

import com.example.BankApplication.auth.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;


    @GetMapping("/{token}")
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
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            System.out.println(appUser.getCreationDate());
            return "registration_form";
        }
        registrationService.registerNewAppUser(appUser);
            return "redirect:/login";
    }

}
