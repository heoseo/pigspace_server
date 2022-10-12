//package com.pigplace.member.service;
//
//import java.util.UUID;
//
//import javax.transaction.Transactional;
//
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.stereotype.Service;
//
//import com.pigplace.common.entity.EmailAuth;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@EnableAsync
//@RequiredArgsConstructor
//public class JoinService {
//
//	@Transactional
//	public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto requestDto) {
//	    validateDuplicated(requestDto.getEmail());
//	    EmailAuth emailAuth = emailAuthRepository.save(
//	            EmailAuth.builder()
//	                    .email(requestDto.getEmail())
//	                    .authToken(UUID.randomUUID().toString())
//	                    .expired(false)
//	                    .build());
//
//	    Member member = memberRepository.save(
//	            Member.builder()
//	                    .email(requestDto.getEmail())
//	                    .password(passwordEncoder.encode(requestDto.getPassword()))
//	                    .provider(null)
//	                    .emailAuth(false)
//	                    .build());
//
//	    emailService.send(emailAuth.getEmail(), emailAuth.getAuthToken());
//	    return MemberRegisterResponseDto.builder()
//	            .id(member.getId())
//	            .email(member.getEmail())
//	            .authToken(emailAuth.getAuthToken())
//	            .build();
//	}
//}
