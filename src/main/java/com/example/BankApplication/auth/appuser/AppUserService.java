package com.example.BankApplication.auth.appuser;

import com.example.BankApplication.auth.registration.token.ConfirmationToken;
import com.example.BankApplication.auth.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG="user with email %s not found";
    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)))
                ;
    }

    public String signUpUser(AppUser appUser){

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepo.save(appUser);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken =new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void enableAppUser(String email){
        appUserRepo.enableAppUser(email);
    }

    public Optional<AppUser> findAppUserByEmail(String email){
        return appUserRepo.findByEmail(email);
    }

    public boolean isEmailRegistered(final String email) {
        return appUserRepo.findOneByEmail(email).isPresent();
    }

    public Optional<AppUser> findAppUserByPassword(String password){return appUserRepo.findByPassword(password);}
}
