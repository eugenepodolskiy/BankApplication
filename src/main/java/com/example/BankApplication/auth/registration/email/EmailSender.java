package com.example.BankApplication.auth.registration.email;

public interface EmailSender {
    void send(String to, String email);
}
