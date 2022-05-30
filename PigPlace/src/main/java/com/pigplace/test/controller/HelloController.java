package com.pigplace.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pigplace.test.vo.TestVO;

@Controller
public class HelloController {
	
	@GetMapping("/testvo")
	public @ResponseBody TestVO testvo() {
		TestVO testVO = new TestVO();
		testVO.setId("ID");
		testVO.setPw("PW");
		return testVO;
	}
} 