package com.pigspace.common.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigspace.common.service.EmailService;
import com.pigspace.common.support.ControllerSupport;
import com.pigspace.common.support.ResponseEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class EmailController extends ControllerSupport{

	private final EmailService emailService;

	@GetMapping("/confirm-email/{tokenId}")
	public ResponseEntity<?> viewConfirmEmail(@PathVariable("tokenId") String tokenId) {
		try {
			emailService.verifyEmail(tokenId, "signup");
		} catch (Exception e) {
			return getFailResponse(400, "인증 실패. 재가입 필요");
		}
		return getOkResponse();
	}

}