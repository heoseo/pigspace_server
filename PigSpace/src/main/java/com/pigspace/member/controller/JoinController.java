package com.pigspace.member.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigspace.common.support.ControllerSupport;
import com.pigspace.common.support.PigException;
import com.pigspace.common.support.ResponseEntity;
import com.pigspace.common.util.StringUtil;
import com.pigspace.comn.entity.EmailToken;
import com.pigspace.comn.entity.UserInfo;
import com.pigspace.comn.repository.UserInfoRepository;
import com.pigspace.comn.service.EmailSenderService;
import com.pigspace.comn.service.EmailTokenService;
import com.pigspace.comn.vo.MailContentBuilder;
import com.pigspace.member.service.JoinService;
import com.pigspace.member.vo.CheckIdRVO;
import com.pigspace.member.vo.EmailVO;
import com.pigspace.member.vo.JoinDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원가입", description = "회원가입")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class JoinController extends ControllerSupport{

	private final UserInfoRepository userInfoRepository;

	private final JoinService joinService;

	private final EmailTokenService emailTokenService;
	private final MailContentBuilder mailContentBuilder;
	private final EmailSenderService emailSenderService;


	/**
     * 회원가입
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> join(@RequestBody JoinDTO pvo) {
    	System.out.println("@@@@@@@@@@@@@" + pvo);

    	if(StringUtil.isNullOrEmpty(pvo.getUserNm() )
			|| StringUtil.isNullOrEmpty(pvo.getUserId())
    		|| StringUtil.isNullOrEmpty(pvo.getUserPw() )
    		|| StringUtil.isNullOrEmpty(pvo.getPhoneNo() )
    		)
    	{
    		return getFailResponse(400, "�ʼ��� ����");
    	}



    	try{
    		joinService.signup(pvo);
    		return getOkResponse();
    	} catch(Exception e) {
    		return getFailResponse();
    	}


    }


    /**
     * 이메일 중복 확인
     * @param userId
     * @return
     */
    @GetMapping("/checkId/{userId}")
	public ResponseEntity<?> getTest(@PathVariable("userId") String userId) {
    	Optional<UserInfo> optUser = userInfoRepository.findByUserId(userId);

    	UserInfo userInfo = null;
    	if(optUser.isPresent()) userInfo = optUser.get();

    	System.out.println("##########user >> " + userInfo);

    	CheckIdRVO rvo = new CheckIdRVO();
    	rvo.setCheckIdYn(userInfo != null ? "N" : "Y");

		return getOkResponse(rvo);

	}

    @GetMapping("/mailTest/{userId}")
    public ResponseEntity<?> mailTest(@PathVariable("userId") String userId) throws Exception{

    	Optional<UserInfo> optUser = userInfoRepository.findByUserId(userId);

    	UserInfo userInfo = null;
    	if(optUser.isPresent()) userInfo = optUser.get();

    	System.out.println("##########user >> " + userInfo);
    	System.out.println("##########user >> " + userInfo.getUserId());





    	EmailToken emailToken = emailTokenService.createEmailToken(userInfo.getMbrNo(),userInfo.getUserId());

		String message = mailContentBuilder.signupBuild(emailToken.getId());
		EmailVO emailVO = new EmailVO();
		emailVO.setReceiverEmail(userInfo.getUserId());
		emailVO.setSubject("PigSpace�� �����Ͻ� ���� ȯ���մϴ�.");
		emailVO.setText(message);

        try {
			emailSenderService.sendEmail(emailVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getFailResponse();
		}
    	return getOkResponse();
    }

    @GetMapping("/exceptionTest")
    public ResponseEntity<?> exceptionTest(){
    	throw new PigException("에러!");
    }



}


