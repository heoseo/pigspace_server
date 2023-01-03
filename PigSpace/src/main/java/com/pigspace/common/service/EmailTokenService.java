package com.pigspace.common.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.pigspace.common.entity.EmailToken;
import com.pigspace.common.repository.EmailTokenRepository;
import com.pigspace.common.support.PigException;
import com.pigspace.common.support.ServiceSupport;
import com.pigspace.common.util.DateUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailTokenService extends ServiceSupport{

	private static final long serialVersionUID = 1925260844761833096L;

	private final EmailTokenRepository emailTokenRepository;


	// 이메일 인증 토큰 생성
	public EmailToken createEmailToken(String mbrNo, String receiverEmail, int expiredDay, String verifyType) {

		Assert.notNull(mbrNo, "mbrNo는 필수입니다");
		Assert.hasText(receiverEmail, "receiverEmail은 필수입니다.");

		// 이메일 토큰 저장
		EmailToken emailToken = EmailToken.createEmailToken(mbrNo, expiredDay, verifyType);
		debug("EmailToken >> " + emailToken);
		emailTokenRepository.save(emailToken);


		return emailToken;

	}

	// 유효한 토큰 가져오기
	@Transactional
	public EmailToken findByEmailTokenId(String emailTokenId, String verifyType) throws Exception {

		Optional<EmailToken> optEmailToken = emailTokenRepository.findByTokenAndVerifyType(emailTokenId, verifyType);

		EmailToken emailToken = null;
		try{
			if(optEmailToken.isPresent()) emailToken = optEmailToken.get();
			else throw new PigException("일치하는 이메일 토큰 미존재");

			if(emailToken.getExpirationDatetime().compareTo(DateUtil.getCurrentTime("yyyyMMddHHmmss")) < 0)
			{
				throw new PigException("메일인증 만료됨");
			}

		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return emailToken;

	}

	// 인증 메일 만료 여부 확인
	@Transactional
	public boolean checkExpired(String mbrNo, String verifyType) throws Exception{

		Optional<EmailToken> optEmailToken = emailTokenRepository.findByMbrNoAndVerifyType(mbrNo, verifyType);

		EmailToken emailToken = null;
		if(optEmailToken.isPresent()) emailToken = optEmailToken.get();

		debug("@@@@@@@@@@@@@@@@@@ emailToken {}", emailToken);
		if(emailToken != null) {
			return emailToken.getExpirationDatetime().compareTo(DateUtil.getCurrentTime("yyyyMMddHHmmss")) < 0
					? true : false;
		}else {
			// 이메일 토큰 정보 없으면 true
			return true;
		}



	}

}