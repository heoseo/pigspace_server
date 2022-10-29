package com.pigspace.member.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigspace.common.entity.UserInfo;
import com.pigspace.common.repository.UserInfoRepository;
import com.pigspace.common.support.ControllerSupport;
import com.pigspace.common.support.ResponseEntity;
import com.pigspace.common.util.StringUtil;
import com.pigspace.member.vo.LoginDTO;
import com.pigspace.member.vo.LoginRVO;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginController extends ControllerSupport{

	private final UserInfoRepository userInfoRepository;



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
	        System.out.println(jsonObj.get("result")); //sim
//	        System.out.println(jsonObj.get("userPw")); //simpw
//	        System.out.println(jsonObj.get("userInfo")); // {"sex":"male","age":50}
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


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO pvo){

		UserInfo user = null;
		LoginRVO loginRVO = new LoginRVO();

		Optional<UserInfo> optUser = userInfoRepository.findByUserIdAndUserPw(pvo.getUserId(), pvo.getUserPw());

		if(optUser.isPresent()) {
			user = optUser.get();
			System.out.println("@@@@@@@@@@@@@@@@"+user);
			loginRVO.setMbrNo(user.getMbrNo());
			loginRVO.setVerifiedYN(user.getVerified() ? "Y" : "N");
			loginRVO.setNickSetYN(StringUtil.isNullOrEmpty(user.getNickname()) ? "N" : "Y");
			return getOkResponse(loginRVO);
		}else
			return getFailResponse(400, "로그인 정보 없음");


	}
}
