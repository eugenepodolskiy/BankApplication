package com.example.BankApplication.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepo extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByPassword(String password);

    @Query("SELECT a FROM AppUser as a WHERE UPPER(a.email) LIKE UPPER(?1)")
    Optional<AppUser> findOneByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE AppUser a " +
            "SET a.enabled=true " +
            "WHERE a.email=?1")
    int enableAppUser(String email);

    @Query("SELECT a FROM AppUser as a WHERE UPPER(a.email) LIKE UPPER(?1)")
    AppUser findAppUserByEmail(String email);
}
