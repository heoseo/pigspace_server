package com.pigplace.member.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigplace.common.entity.UserInfo;
import com.pigplace.common.repository.UserInfoRepository;
import com.pigplace.common.util.DateUtil;
import com.pigplace.common.util.StringUtil;
import com.pigplace.common.vo.ControllerSupport;
import com.pigplace.common.vo.ResponseEntity;
import com.pigplace.member.vo.CheckIdRVO;
import com.pigplace.member.vo.JoinDTO;

import lombok.RequiredArgsConstructor;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping("/member")
public class JoinController extends ControllerSupport{

	private final UserInfoRepository userInfoRepository;

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
    		return getFailResponse(400, "필수값 누락");
    	}

    	String joinDatetime = DateUtil.getCurrentTime("yyyyMMddHHmmss");
    	joinDatetime = joinDatetime.substring(2, 8);
    	Integer hash = ( joinDatetime + "|" + pvo.getUserNm() + "|" + pvo.getUserId() ).hashCode();


        final UserInfo user = UserInfo.builder()
        		.mbrNo("1"+(hash < 0 ? hash * -1 : hash ))
        		.userNm(pvo.getUserNm())
        		.userId(pvo.getUserId())
        		.userPw(pvo.getUserPw())
        		.phoneNo(pvo.getPhoneNo())
        		.joinDatetime(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
        		.firstJoinDatetime(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
        		.validateYn("Y")
        		.userType("U")
        		.joinType("U")
                .build();


        if(userInfoRepository.save(user) != null) {
        	return getOkResponse();
        }else {
        	return getFailResponse();
        }

    }

    /**
     * 이메일 중복 확인
     * @param name
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


}


