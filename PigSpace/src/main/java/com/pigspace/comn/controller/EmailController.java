package com.pigspace.comn.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigspace.common.support.ControllerSupport;
import com.pigspace.common.support.ResponseEntity;
import com.pigspace.comn.service.EmailService;

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
			boolean result = emailService.verifyEmail(tokenId);
//			return new BaseResponse<>(result);
		} catch (Exception exception) {
//			return new BaseResponse<>(exception.getStatus());
		}
		return getOkResponse();
	}

}