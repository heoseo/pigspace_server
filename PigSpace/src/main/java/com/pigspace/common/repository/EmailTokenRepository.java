package com.pigspace.common.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pigspace.common.entity.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {
	Optional<EmailToken> findByIdAndVerifyType(String emailTokenId, String verifyType);
	Optional<EmailToken> findByMbrNoAndVerifyType(String mbrNo, String verifyType);
	Optional<EmailToken> findByIdAndExpirationDatetimeAndIsExpired(String emailTokenId, LocalDateTime now, boolean isExpired);
}