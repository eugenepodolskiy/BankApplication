package com.example.BankApplication.card;

import com.example.BankApplication.appuser.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Card {

    @SequenceGenerator(name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "card_sequence")
    private Integer id;
    @Min(value = 1000000000000000L, message = "Enter card number")
    @Max(value = 9999999999999999L, message = "Enter card number")
    private Long cardNumber;
    @Min(value = 100, message = "Enter CVV")
    @Max(value = 999, message = "Enter CVV")
    private Integer cardCVV;
    private String cardED; // expire date
    @NotNull(message = "Enter your balance")
    private Double balance;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyType currencyType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public Card(Long cardNumber, String cardED, Integer cardCVV, Double balance, CurrencyType currencyType, AppUser appUser) {
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
        this.cardED = cardED;
        this.balance = balance;
        this.currencyType = currencyType;
        this.appUser = appUser;
    }
}
