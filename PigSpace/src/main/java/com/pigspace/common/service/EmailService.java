package com.pigspace.common.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pigspace.common.entity.EmailToken;
import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.support.PigException;
import com.pigspace.common.support.ServiceSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
//https://interconnection.tistory.com/m/123
@Transactional(readOnly = true)
public class EmailService extends ServiceSupport{

	private static final long serialVersionUID = 8441672845393206241L;

	private final EmailTokenService emailTokenService;
	private final UserInfoRepository userInfoRepository;

	@Transactional
	public boolean verifyEmail(String token, String verifyType) throws Exception {
		EmailToken findEmailToken = null;

		findEmailToken = emailTokenService.findByEmailTokenId(token, verifyType);

		Optional<UserInfo> findMember = userInfoRepository.findByMbrNo(findEmailToken.getMbrNo());
		findEmailToken.setIsExpired();

		if (findMember.isPresent()) {
			UserInfo member = findMember.get();
			member.setIsVerified();
			return true;
		} else {
			throw new PigException("이메일 인증 토큰과 일치하는 회원정보 없음");
		}
	}
}