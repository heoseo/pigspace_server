package com.pigplace.member.service;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.pigplace.common.support.PigException;
import com.pigplace.common.util.DateUtil;
import com.pigplace.comn.entity.EmailToken;
import com.pigplace.comn.entity.UserInfo;
import com.pigplace.comn.repository.UserInfoRepository;
import com.pigplace.comn.service.EmailSenderService;
import com.pigplace.comn.service.EmailTokenService;
import com.pigplace.comn.vo.MailContentBuilder;
import com.pigplace.member.vo.EmailVO;
import com.pigplace.member.vo.JoinDTO;

import lombok.RequiredArgsConstructor;

@Service
@EnableAsync
@RequiredArgsConstructor
public class JoinService {

	private final UserInfoRepository userInfoRepository;

	private final EmailTokenService emailTokenService;
	private final EmailSenderService emailSenderService;
	private final MailContentBuilder mailContentBuilder;

	public void signup(JoinDTO pvo) throws Exception{
		String joinDatetime = DateUtil.getCurrentTime("yyyyMMddHHmmss");
    	joinDatetime = joinDatetime.substring(2, 8);
    	Integer hash = ( joinDatetime + "|" + pvo.getUserNm() + "|" + pvo.getUserId() ).hashCode();


    	// UserInfo
        final UserInfo user = UserInfo.builder()
        		.mbrNo(DateUtil.getCurrentTime("yyMMdd")+(hash < 0 ? hash * -1 : hash ))
        		.userNm(pvo.getUserNm())
        		.userId(pvo.getUserId())
        		.userPw(pvo.getUserPw())
        		.phoneNo(pvo.getPhoneNo())
        		.joinDatetime(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
        		.firstJoinDatetime(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
        		.validateYn("Y")
        		.userType("U")
        		.joinType("U")
        		.verified(false)
                .build();

        UserInfo userInfo = userInfoRepository.save(user);
        if(userInfo == null)
        	throw new PigException("UserInfo 없음");


        //토큰 생성
        EmailToken emailToken = emailTokenService.createEmailToken(user.getMbrNo(), user.getUserId());

		String message = mailContentBuilder.signupBuild(emailToken.getId());

		EmailVO emailVO = new EmailVO();
		emailVO.setReceiverEmail(user.getUserId());
		emailVO.setSubject("PigSpace가입을 환영합니다.");
		emailVO.setText(message);

        try {
			emailSenderService.sendEmail(emailVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
