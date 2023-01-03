package com.pigspace.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.service.EmailTokenService;
import com.pigspace.common.support.ControllerSupport;
import com.pigspace.common.support.ResponseEntity;
import com.pigspace.common.util.StringUtil;
import com.pigspace.member.service.LoginService;
import com.pigspace.member.vo.LoginDTO;
import com.pigspace.member.vo.LoginRVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Tag(name = "로그인", description = "로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginController extends ControllerSupport{

	private static final long serialVersionUID = -6619689232216657342L;

	private final EmailTokenService emailTokenService;

	private final LoginService loginService;



	@SuppressWarnings("deprecation")
	@GetMapping("/getTest")
	public ResponseEntity<?> getTest(){
		 //1. Json
        String strJson = "{\"result\":\"SUCCESS\""
//                        + "\"userPw\":\"simpw\","
//                        + "\"userInfo\":{"
//                            + "\"age\":50,"
//                            + "\"sex\":\"male\""
//                            + "}"
                        + "}";

        //2. Parser
        JSONParser jsonParser = new JSONParser();

        //3. To Object
        Object obj;
		try {
			obj = jsonParser.parse(strJson);

	        //4. To JsonObject
	        JSONObject jsonObj = (JSONObject) obj;

	        //print
	        debug("result:{}",jsonObj.get("result")); //sim
//	        debug(jsonObj.get("userPw")); //simpw
//	        debug(jsonObj.get("userInfo")); // {"sex":"male","age":50}
	        return getOkResponse(200, "OK", obj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getFailResponse();
	}

	@GetMapping("/getTest/{name}")
	public ResponseEntity<?> postTest(@PathVariable("name") String name) {
        return getOkResponse(200, name);
	}


	/**
     * 로그인
     */
	@Operation(summary = "로그인", description = "<strong>로그인</strong>")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content(schema = @Schema(implementation = LoginRVO.class)))})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO pvo){

		LoginRVO loginRVO = new LoginRVO();

		UserInfo user = null;

		try {
			user = loginService.login(pvo.getUserId(), pvo.getUserPw());


			if(user != null) {

				loginRVO.setMbrNo(user.getMbrNo());
				loginRVO.setExpiredYN(emailTokenService.checkExpired(user.getMbrNo(), "S") ? "Y" : "N");
				loginRVO.setVerifiedYN(user.getVerifiedYn() ? "Y" : "N");
				loginRVO.setNickSetYN(StringUtil.isNullOrEmpty(user.getNickname()) ? "N" : "Y");
			}else
				return getFailResponse(400, "로그인 정보 없음");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getOkResponse(loginRVO);
	}
}
