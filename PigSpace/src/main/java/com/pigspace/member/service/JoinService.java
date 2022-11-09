package com.pigspace.member.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pigspace.common.entity.EmailToken;
import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.service.EmailSenderService;
import com.pigspace.common.service.EmailTokenService;
import com.pigspace.common.support.ServiceSupport;
import com.pigspace.common.util.DateUtil;
import com.pigspace.common.util.HashUtil;
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
    	Integer hash = HashUtil.makeMbrHash(pvo.getUserNm(), pvo.getUserId(), pvo.getPhoneNo());


    	// UserInfo
        final UserInfo user = UserInfo.builder()
        		.mbrNo(DateUtil.getCurrentTime("yyMMdd")+(hash < 0 ? hash * -1 : hash ))
        		.userNm(pvo.getUserNm())
        		.userId(pvo.getUserId())
        		.userPw(pvo.getUserPw())
        		.phoneNo(pvo.getPhoneNo())
        		.joinDatetime(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
        		.firstJoinDatetime(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
        		.validYn(true)
        		.userType("U")
        		.joinType("U")
        		.verifiedYn(false)
        		.createdDate(LocalDateTime.now())
                .build();

        //재가입 시 createDate가 있으므로 update됨.
        userInfoRepository.save(user);


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
		//TODO 인증 전 일경우에도 검색됨.
		Optional<UserInfo> optUser = userInfoRepository.findByUserId(userId);

    	UserInfo userInfo = null;
    	if(optUser.isPresent()) userInfo = optUser.get();
    	debug("userinfo:{}",userInfo);
    	debug(DateUtil.getCurrentTimePlusDays("yyyyMMddHHmmss", 1));

    	if(userInfo == null)
    		return "Y";		// 입력된 정보 없으므로 가입 가능.
    	else {
    		if(!userInfo.getValidYn())
    			return "Y";	// 탈퇴한 메일이므로 가입 가능.
    		else
    			return "N";	// 아직 유효한 아이디이므로 가입 불가.
    	}

	}
}
