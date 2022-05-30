package com.pigplace.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigplace.common.vo.ResponseApiEntity;
import com.pigplace.test.vo.TestVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class RestTestController {
	
	
	@ApiOperation(value="Test 서비스", notes="이 API에 대한 설명입니다.")
	@ApiResponses({
	        @ApiResponse(code = 200, message = "API 정상 작동"),
	        @ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/swaggerTest")
	public ResponseApiEntity swaggerTest(@RequestBody TestVO testVO) throws Exception {
		ResponseApiEntity response = new ResponseApiEntity();
		response.add("test", testVO);
		
		

		System.out.println("testVO" + testVO.toString());
		System.out.println("response" + response.toString());
	    return response;
	}
	
	@GetMapping("/getTest")
	public String getTest(){
		return "Hello World!";
	}
	
	@PostMapping("/postTest")
	public String postTest() {
		return "Hello World!";
	}
	
	@GetMapping("/testvo")
	public TestVO testvo() {
		TestVO testVO = new TestVO();
		testVO.setId("ID");
		testVO.setPw("PW");
		return testVO;
	}
	
}
	