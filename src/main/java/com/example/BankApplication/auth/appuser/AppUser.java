package com.example.BankApplication.auth.appuser;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
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
    private String firstName;
    @Column(nullable = false)
    private String secondName;
    @Column(unique = true,
    nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String password;
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

    public AppUser(String firstName, String secondName, String email, Integer age, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.password = password;
    }
}
