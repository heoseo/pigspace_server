package com.pigspace.member.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <h1>로그인 응답 VO</h1>
 * @author heoseo
 *
 */
@Data
public class LoginRVO implements Serializable{

	private static final long serialVersionUID = -6799977860974447667L;


	@Schema(description = "회원번호", example = "YYMMDD*")
	private String mbrNo;

	@Schema(description = "인증만료여부", example = "Y: 만료됨(재가입 필요), N: 만료안됨")
	private String expiredYN;

	@Schema(description = "인증여부", example = "Y: 인증O, N: 인증X(메일 인증 필요)")
	private String verifiedYN;

	@Schema(description = "닉네임설정여부", example = "Y: 설정함, N: 설정 안 함(닉네임 설정 필요)")
	private String nickSetYN;

}