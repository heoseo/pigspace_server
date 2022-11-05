package com.pigspace.member.service;

import java.util.Optional;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pigspace.common.entity.EmailToken;
import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.service.EmailSenderService;
import com.pigspace.common.service.EmailTokenService;
import com.pigspace.common.support.PigException;
import com.pigspace.common.support.ServiceSupport;
import com.pigspace.common.util.DateUtil;
import com.pigspace.common.vo.MailContentBuilder;
import com.pigspace.member.vo.EmailVO;
import com.pigspace.member.vo.JoinDTO;

import lombok.RequiredArgsConstructor;

@Service
@EnableAsync
@RequiredArgsConstructor
public class JoinService extends ServiceSupport{

	private static final long serialVersionUID = 5393706656774430669L;

	private final UserInfoRepository userInfoRepository;

	private final EmailTokenService emailTokenService;
	private final EmailSenderService emailSenderService;
	private final MailContentBuilder mailContentBuilder;

	/**
	 * 회원가입 요청
	 * @param pvo
	 * @throws Exception
	 */
	@Transactional
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
        		.isValid(true)
        		.userType("U")
        		.joinType("U")
        		.isVerified(false)
                .build();

        UserInfo userInfo = userInfoRepository.save(user);
        if(userInfo == null)
        	throw new PigException("UserInfo 없음");


        //토큰 생성
        debug(DateUtil.getCurrentTimePlusDays("yyyyMMddHHmmss", 1));
        EmailToken emailToken = emailTokenService.createEmailToken(user.getMbrNo(), user.getUserId(), 1, "signup");

		String message = mailContentBuilder.signupBuild(emailToken.getId());

		EmailVO emailVO = new EmailVO();
		emailVO.setReceiverEmail(user.getUserId());
		emailVO.setSubject("PigSpace가입을 환영합니다.");
		emailVO.setText(message);

        try {
			emailSenderService.sendEmail(emailVO);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 아이디 중복 확인
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String checkId(String userId) throws Exception{
		//TODO 탈퇴한 메일로 재가입 가능하도록 하는지
		Optional<UserInfo> optUser = userInfoRepository.findByUserId(userId);

    	UserInfo userInfo = null;
    	if(optUser.isPresent()) userInfo = optUser.get();
    	debug("userinfo:{}",userInfo);
    	debug(DateUtil.getCurrentTimePlusDays("yyyyMMddHHmmss", 1));


    	return userInfo != null ? "N" : "Y";
	}
}
