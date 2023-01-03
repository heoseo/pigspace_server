package com.pigspace.member.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.service.EmailSenderService;
import com.pigspace.common.service.EmailTokenService;
import com.pigspace.common.support.ServiceSupport;
import com.pigspace.common.util.DateUtil;
import com.pigspace.common.util.HashUtil;
import com.pigspace.common.vo.MailContentBuilder;
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
		try {
	    	Integer hash = HashUtil.makeHash(pvo.getUserNm(), pvo.getUserId(), pvo.getPhoneNo());


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
//	        		.isCreateMode(false)	// false로 하면 update하란 뜻이므로
	                .build();

	        debug("######## {}", user);
	        debug("######## {}", user.getCreatedDate());

	        // isCreateMode = true 이므로 isNew => 무조건 insert => 에러 처리 필요
	        userInfoRepository.save(user);


	//        //토큰 생성
	//        debug(DateUtil.getCurrentTimePlusDays("yyyyMMddHHmmss", 1));
	//        EmailToken emailToken = emailTokenService.createEmailToken(user.getMbrNo(), user.getUserId(), 1, "S");
	//
	//		String message = mailContentBuilder.signupBuild(emailToken.getToken());
	//
	//		EmailVO emailVO = new EmailVO();
	//		emailVO.setReceiverEmail(user.getUserId());
	//		emailVO.setSubject("PigSpace가입을 환영합니다.");
	//		emailVO.setText(message);
//
//			emailSenderService.sendEmail(emailVO);
		} catch(DataIntegrityViolationException e) {
			debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@ DataIntegrityViolationException");
    	} catch (Exception e) {
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
