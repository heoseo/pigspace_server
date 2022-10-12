//package com.pigplace.member.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pigplace.common.entity.UserInfo;
//import com.pigplace.common.repository.UserInfoRepository;
//import com.pigplace.common.service.EmailService;
//import com.pigplace.common.util.DateUtil;
//import com.pigplace.common.vo.ControllerSupport;
//import com.pigplace.common.vo.ResponseEntity;
//import com.pigplace.member.vo.JoinPVO;
//
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//
//@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
//@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
//@RequestMapping("/api/auth")
//public class AuthController extends ControllerSupport{
//	
//	private EmailService emailService;
//	private final UserInfoRepository userInfoRepository;
//	
//	/**
//     * 인증 버튼 클릭 메일
//     * @return
//     */
//    @RequestMapping("/mail/auth-link")
//    public ResponseEntity<?> join(JoinPVO joinPVO) {
//    	
//        final UserInfo user = UserInfo.builder()
//        		.JOIN_DATETIME(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
//        		.FIRST_JOIN_DATETIME(DateUtil.getCurrentTime("yyyyMMddHHmmss"))
//        		.VALIDATE_YN("Y")
//        		.USER_TYPE("U")
//        		.JOIN_TYPE("U")
//                .build();
//        
//        
//        return getOkResponse(userInfoRepository.save(user));
//    }
//    
//    @ApiOperation(value = "이메일 인증", notes = "이메일 인증을 진행한다.")
//    @GetMapping("/confirm-email")
//    public SingleResult<String> confirmEmail(@ModelAttribute EmailAuthRequestDto requestDto) {
//    	emailService.confirmEmail(requestDto);
//        return responseService.getSingleResult("인증이 완료되었습니다.");
//    }
//
//}
//	