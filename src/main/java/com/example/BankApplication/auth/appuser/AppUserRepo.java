package com.example.BankApplication.auth.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepo extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByEmail(String email);


    @Query("UPDATE AppUser as a " +
            "SET a.enabled=true " +
            "WHERE a.email=?1")
    int enableAppUser(String email);
}
