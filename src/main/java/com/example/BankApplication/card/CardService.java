package com.example.BankApplication.card;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepo cardRepo;
    private final AppUserService appUserService;

    public void addCard(Card card){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userUsername= ((UserDetails)principal).getUsername();
        AppUser appUser=appUserService.findAppUserByUsername(userUsername);
        cardRepo.save(new Card(card.getCardNumber().longValue(),
                card.getCardED(),
                card.getCardCVV(),
                card.getBalance(),
                card.getCurrencyType(),
                appUser));
    }

}
