package com.example.BankApplication.auth.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepo.save(confirmationToken);
    }

    public Optional<ConfirmationToken>getToken(String token){
        return confirmationTokenRepo.findByToken(token);
    }

    public void updateConfirmationToken(String token){
        confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }

}
