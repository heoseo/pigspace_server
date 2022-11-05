package com.pigspace.member.service;

import java.util.Optional;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.support.ServiceSupport;

import lombok.RequiredArgsConstructor;

@Service
@EnableAsync
@RequiredArgsConstructor
public class LoginService extends ServiceSupport{

	private static final long serialVersionUID = -6802810377022087136L;

	private final UserInfoRepository userInfoRepository;

	public UserInfo login(String userId, String userPw) throws Exception{
		Optional<UserInfo> optUser = userInfoRepository.findByUserIdAndUserPw(userId, userPw);

		UserInfo user = null;
		if(optUser.isPresent()) user = optUser.get();

		debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + user);
		return user;

	}
}
