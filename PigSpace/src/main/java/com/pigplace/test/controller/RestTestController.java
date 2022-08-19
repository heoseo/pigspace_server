package com.pigplace.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigplace.common.vo.ControllerSupport;
import com.pigplace.common.vo.ResponseEntity;
import com.pigplace.test.entity.MemberEntity;
import com.pigplace.test.repository.MemberRepository;
import com.pigplace.test.vo.TestVO;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping("/api/test")
public class RestTestController extends ControllerSupport{
	
	private final MemberRepository memberRepository;

    /**
     * 멤버 조회
     * @return
     */ 
    @GetMapping("member")
    public ResponseEntity<?> findAllMember() {
        List<MemberEntity> memberList = memberRepository.findAll();
        
        return getOkResponse("조회성공~!", memberList);
    }

    /**
     * 회원가입
     * @return
     */
    @PostMapping("member")
    public ResponseEntity<?> signUp() {
        final MemberEntity member = MemberEntity.builder()
                .username("test_user@gmail.com")
                .name("test user")
                .build();
        
//        memberRepository.save(member);
        
        return getOkResponse(memberRepository.save(member));
    }
    
	@SuppressWarnings("deprecation")
	@GetMapping("/getTest")
	public ResponseEntity<?> getTest(){
		 //1. Json 문자열
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
	
	@PostMapping("/postTest")
	public ResponseEntity<?> postTest() {
        return getOkResponse(200, "SUCCESS");
	}
	
	@GetMapping("/get")
	public ResponseEntity<TestVO> testvo() {
		TestVO testVO = new TestVO();
		testVO.setId("ID"); 
		testVO.setPw("PW");
		
		List<TestVO> testList = new ArrayList<>();
		testList.add(testVO);
		testList.add(testVO);
		
//		return getOkResponse(testList);
//		return getOkResponse("조회성공~!", testList);
		return getFailResponse("조회실패...");
	}
	
}
	