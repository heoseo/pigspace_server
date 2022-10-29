package com.pigspace.comn.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pigspace.comn.entity.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {
	Optional<EmailToken> findByIdAndExpirationDateAfterAndExpired(String emailTokenId, LocalDateTime now, boolean expired);
}