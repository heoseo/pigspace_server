package com.pigspace.member.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigspace.common.entity.EmailToken;
import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.service.EmailSenderService;
import com.pigspace.common.service.EmailTokenService;
import com.pigspace.common.support.ControllerSupport;
import com.pigspace.common.support.PigException;
import com.pigspace.common.support.ResponseEntity;
import com.pigspace.common.util.StringUtil;
import com.pigspace.common.vo.MailContentBuilder;
import com.pigspace.member.service.JoinService;
import com.pigspace.member.vo.CheckIdRVO;
import com.pigspace.member.vo.EmailVO;
import com.pigspace.member.vo.JoinDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원가입", description = "회원가입")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class JoinController extends ControllerSupport{

	private static final long serialVersionUID = -2606895170405613501L;

	private final UserInfoRepository userInfoRepository;

	private final JoinService joinService;

	private final EmailTokenService emailTokenService;
	private final MailContentBuilder mailContentBuilder;
	private final EmailSenderService emailSenderService;

	/**
     * 회원가입 요청
     */
	@Operation(summary = "회원가입 요청", description = "<strong>회원가입 요청</strong>\n\n200 - Y:사용가능 N: 사용불가\n\n400 - 필수값 누락\n\n500 - Server Error")
    @PostMapping("/signup")
    public ResponseEntity<?> join(@RequestBody JoinDTO pvo) {

		try{
			debug("@@@@@@@@@@@@ {}", pvo);
	    	if(StringUtil.isNullOrEmpty(pvo.getUserNm() )
				|| StringUtil.isNullOrEmpty(pvo.getUserId())
	    		|| StringUtil.isNullOrEmpty(pvo.getUserPw() )
	    		|| StringUtil.isNullOrEmpty(pvo.getPhoneNo() )
	    		)
	    	{
	    		return getFailResponse(400, "필수값 누락");
	    	}

    		joinService.signup(pvo);
    		return getOkResponse();
    	} catch(Exception e) {
			error(e.getMessage(), e);
    		return getFailResponse(e.getMessage());
    	}


    }


    /**
     * 아이디 중복 확인
     * @param userId
     * @return
     */
	@Operation(summary = "아이디 중복 확인", description = "<strong>아이디 중복 확인</strong>")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "이메일 중복 확인 성공", content = @Content(schema = @Schema(implementation = CheckIdRVO.class)))})
	@Parameters({@Parameter(name = "userId", description = "중복 확인할 아이디", example = "test@gmail.com")	})
    @GetMapping("/checkId/{userId}")
	public ResponseEntity<?> getTest(@PathVariable("userId") String userId) {

		if(StringUtil.isNullOrEmpty(userId))
			return getFailResponse(400, "필수값 누락");



    	CheckIdRVO rvo = new CheckIdRVO();
    	try {
			rvo.setCheckIdYn(joinService.checkId(userId));
		} catch (Exception e) {
			return getFailResponse(e.getMessage());
		}

    	debug("debug!!!!!!!!!");
    	debug("debug@@@@@@@@@ {} {}", rvo, "asdf");
    	info("####### info user >> {}", rvo);

		return getOkResponse(rvo);

	}

    @GetMapping("/mailTest/{userId}")
    public ResponseEntity<?> mailTest(@PathVariable("userId") String userId) throws Exception{

    	Optional<UserInfo> optUser = userInfoRepository.findByUserId(userId);

    	UserInfo userInfo = null;
    	if(optUser.isPresent()) userInfo = optUser.get();

    	debug("##########user >> " + userInfo);
    	debug("##########user >> " + userInfo.getUserId());





    	EmailToken emailToken = emailTokenService.createEmailToken(userInfo.getMbrNo(),userInfo.getUserId(), 1, "test");

		String message = mailContentBuilder.signupBuild(emailToken.getToken());
		EmailVO emailVO = new EmailVO();
		emailVO.setReceiverEmail(userInfo.getUserId());
		emailVO.setSubject("PigSpace 회원가입을 환영합니다.");
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
    	try{
    		throw new PigException("에러!");
    	}catch(Exception e) {
    		error(e.getMessage());
    		throw e;
    	}
    }



}


