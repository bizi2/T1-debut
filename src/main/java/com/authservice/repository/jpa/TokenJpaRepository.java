package com.authservice.repository.jpa;

import com.authservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenAndRevokedFalse(String token);

    @Transactional
    @Modifying
    @Query("DELETE FROM Token t WHERE t.expiryDate < ?1")
    void deleteAllExpiredSince(Instant now);
}