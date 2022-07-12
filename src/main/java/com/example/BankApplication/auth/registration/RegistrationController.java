package com.example.BankApplication.auth.registration;

import com.example.BankApplication.auth.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;


    @GetMapping("/{token}")
    public String confirmAppUser(@RequestParam("token") String token){
        registrationService.confirmToken(token);
        return "confirmed";
    }

 /* @GetMapping
    public String registrationForm(AppUser appUser,Model model){
        model.addAttribute("appUser", appUser);
        return "registration_form";
    }*/

    @GetMapping
    public void registerAppUser(@RequestBody AppUser appUser, Model model){
        model.addAttribute("appUser", appUser);
        registrationService.registerNewAppUser(appUser);
       // return "redirect:/login";
    }

}
