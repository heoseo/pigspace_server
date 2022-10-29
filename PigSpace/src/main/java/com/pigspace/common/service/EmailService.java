package com.pigspace.common.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pigspace.common.entity.EmailToken;
import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
//https://interconnection.tistory.com/m/123
@Transactional(readOnly = true)
public class EmailService {

	private final EmailTokenService emailTokenService;
	private final UserInfoRepository userInfoRepository;

//	@Override
	@Transactional
	public boolean verifyEmail(String token) throws Exception {
		EmailToken findEmailToken = emailTokenService.findByIdAndExpirationDateAfterAndExpired(token);

		Optional<UserInfo> findMember = userInfoRepository.findByMbrNo(findEmailToken.getMbrNo());
		findEmailToken.setTokenToUsed();

		if (findMember.isPresent()) {
			UserInfo member = findMember.get();
//			https://blossom6729.tistory.com/m/14
			member.setVerified();
			return true;
		} else {
			throw new Exception("DATABASE_ERROR");
		}
	}
}