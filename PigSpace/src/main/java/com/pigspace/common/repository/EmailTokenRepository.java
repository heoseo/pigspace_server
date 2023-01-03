package com.pigspace.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pigspace.common.entity.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {
	Optional<EmailToken> findByTokenAndVerifyType(String token, String verifyType);
	Optional<EmailToken> findByMbrNoAndVerifyType(String mbrNo, String verifyType);
}