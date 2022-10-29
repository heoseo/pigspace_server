package com.pigspace.comn.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.pigspace.comn.entity.EmailToken;
import com.pigspace.comn.repository.EmailTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailTokenService {

	private final EmailTokenRepository emailTokenRepository;


	// 이메일 인증 토큰 생성
	public EmailToken createEmailToken(String mbrNo, String receiverEmail) {

		Assert.notNull(mbrNo, "mbrNo는 필수입니다");
		Assert.hasText(receiverEmail, "receiverEmail은 필수입니다.");

		// 이메일 토큰 저장
		EmailToken emailToken = EmailToken.createEmailToken(mbrNo);
		System.out.println("EmailToken >> " + emailToken);
		emailTokenRepository.save(emailToken);


		return emailToken;

	}

	// 유효한 토큰 가져오기
	public EmailToken findByIdAndExpirationDateAfterAndExpired(String emailTokenId) throws Exception {
		Optional<EmailToken> emailToken = emailTokenRepository
			.findByIdAndExpirationDateAfterAndExpired(emailTokenId, LocalDateTime.now(), false);

		// 토큰이 없다면 예외 발생
		return emailToken.orElseThrow(() -> new Exception("BaseResponseStatus.DATABASE_ERROR"));
	}

}