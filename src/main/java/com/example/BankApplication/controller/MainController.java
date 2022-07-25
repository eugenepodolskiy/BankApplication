package com.example.BankApplication.controller;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.card.Card;
import com.example.BankApplication.card.CardService;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
@RequestMapping("/bank")
public class MainController {

    private final CardService cardService;


    //not_authorized

    @GetMapping
    public String mainPage(){
        return "main_page";
    }

    // authorized
    @GetMapping("/add")
    public String getAddCardToUser(Card card , Model model){
        model.addAttribute("card", card);
        return "add_card";
    }
    @PostMapping("/add")
    public String addCardToUser(@RequestBody @Validated @ModelAttribute("card") Card card, BindingResult bindingResult, Model model){
        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");
        if (!card.getCardED().matches("(?:0[1-9]|1[0-2])/[0-9]{2}")){
            bindingResult.addError(new FieldError("card","cardED","Enter expire date"));
        }else {
            if (YearMonth.parse(card.getCardED(),ccMonthFormatter).isBefore(YearMonth.now())){
                bindingResult.addError(new FieldError("card","cardED","Your card is now inactive"));
            }
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "add_card";
        }
        System.out.println(YearMonth.parse(card.getCardED(),ccMonthFormatter));
        System.out.println(YearMonth.now());
        cardService.addCard(card);
        return "redirect:/bank";
    }

    }
