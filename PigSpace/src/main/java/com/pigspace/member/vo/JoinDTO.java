package com.pigspace.member.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <h1>회원가입 vo</h1>
 * @author heoseo
 *
 */
@Data
public class JoinDTO implements Serializable{

	private static final long serialVersionUID = -2549328862851803865L;

	@Schema(description = "이름", example = "김허홍")
	private String userNm;

	@Schema(description = "아이디", example = "test@naver.com")
	private String userId;

	@Schema(description = "비밀번호", example = "password")
	private String userPw;

	@Schema(description = "핸드폰 번호", example = "01076619891")
	private String phoneNo;

}