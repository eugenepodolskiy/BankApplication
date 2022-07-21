package com.example.BankApplication.auth.appuser;


import com.example.BankApplication.auth.registration.validator.MinForDateConstraint;
import com.example.BankApplication.auth.registration.validator.UniqueEmailConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class AppUser implements UserDetails {


    @SequenceGenerator(
            name = "app_user_sequence_generator",
            sequenceName = "app_user_sequence_generator",
            allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence_generator")
    private Integer id;
    @Column(nullable = false)
    @Length(min = 3,message = "Enter your first name")
    @NotBlank(message = "Enter your first name")
    private String firstName;
    @Column(nullable = false)
    @Length(min = 3,message = "Enter your second name")
    @NotBlank(message = "Enter your second name")
    private String secondName;
    @Column(nullable = false,unique = true)
    @UniqueEmailConstraint(message ="email already taken")
    @Email(regexp = ".+[@].+[\\.].+",message = "Enter valid email")
    @NotBlank(message = "Enter your email")
    private String email;
    private final LocalDate creationDate=LocalDate.now();
    @NotNull(message = "Enter your birth date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @MinForDateConstraint
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    @NotBlank(message = "Enter your password")
    @Length(min = 8,message = "Password must be at least 8 characters")
    private String password;
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private boolean enabled=false;
    private boolean locked=false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public AppUser(String firstName, String secondName, String email, LocalDate dateOfBirth, String password,String confirmPassword, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.confirmPassword=confirmPassword;
        this.appUserRole=appUserRole;
    }
}
